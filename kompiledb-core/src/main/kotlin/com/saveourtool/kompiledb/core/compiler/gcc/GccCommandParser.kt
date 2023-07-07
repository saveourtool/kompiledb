package com.saveourtool.kompiledb.core.compiler.gcc

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import com.saveourtool.kompiledb.core.compiler.CompilerCommandParser
import com.saveourtool.kompiledb.core.compiler.ParsedCompilerCommand
import com.saveourtool.kompiledb.core.io.Arg
import com.saveourtool.kompiledb.core.io.CommandLineParser
import com.saveourtool.kompiledb.core.io.PathMapper
import com.saveourtool.kompiledb.core.io.PathMapperScope
import com.saveourtool.kompiledb.core.io.mappers.LocalPathMapper.toLocalPath
import com.saveourtool.kompiledb.core.lang.C
import com.saveourtool.kompiledb.core.lang.Cxx
import com.saveourtool.kompiledb.core.lang.Language
import com.saveourtool.kompiledb.core.lang.Language.Companion.UNKNOWN
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import kotlin.Result.Companion.failure
import kotlin.io.path.extension
import kotlin.io.path.isDirectory
import kotlin.io.path.readText

/**
 * Compilation command parser specific for _GCC_ and _Clang_.
 */
internal class GccCommandParser(
    override val pathMapper: PathMapper,
    commandLineParser: CommandLineParser,
) : CompilerCommandParser,
    CommandLineParser by commandLineParser,
    PathMapperScope {

    override fun parse(
        databasePath: Path,
        command: CompilationCommand,
    ): ParsedCompilerCommand {
        val projectRoot = when {
            databasePath.isDirectory() -> databasePath

            else -> {
                val parentDir = databasePath.parent
                require(parentDir != null) {
                    "Database file $databasePath doesn't have a parent"
                }
                parentDir
            }
        }

        val parseErrors = mutableListOf<String>()
        val arguments = command.parsedArguments
            .asSequence()
            .flatMapIndexed { index, argument ->
                when {
                    /*
                     * `argv[0]` is never a response file, even if it starts with a `@`.
                     */
                    index == 0 -> sequenceOf(argument)

                    /*
                     * Read the content of the response file.
                     */
                    argument.isResponseFile -> expandResponseFile(
                        projectRoot,
                        command.directory,
                        EnvPath(argument.substring(1)),
                    )
                        .getOrElse { failure ->
                            /*
                             * Collect errors, if any.
                             */
                            parseErrors += failure.localizedMessageExt ?: failure.toString()
                            emptySequence()
                        }

                    /*
                     * Pass the rest of the arguments as-is.
                     */
                    else -> sequenceOf(argument)
                }
            }
            .toList()

        val compiler = arguments.firstOrNull()?.let(::EnvPath)
            ?: run {
                parseErrors += "The compiler path is empty"
                EnvPath.EMPTY
            }

        val includePaths = mutableMapOf<String, MutableList<EnvPath>>()
        val definedMacros = mutableMapOf<String, String>()
        val undefinedMacros = mutableListOf<String>()
        var language: Language? = null

        val ignoredArguments = arguments.asSequence()
            .drop(1)
            .collectOptionValues(options = INCLUDE_SWITCHES) { option, optionValue ->
                /*
                 * `-I-` is just a separator and should be ignored.
                 */
                if (option + optionValue !in IGNORED_INCLUDE_SWITCHES) {
                    includePaths.compute(option) { _, value ->
                        (value ?: mutableListOf()).apply {
                            this@apply += EnvPath(optionValue)
                        }
                    }
                }
            }
            .collectOptionValues(options = DEFINE_MACRO_SWITCHES) { _, optionValue ->
                val (name, value) = optionValue.splitToNameAndValue()
                definedMacros[name] = value
            }
            .collectOptionValues(options = UNDEFINE_MACRO_SWITCHES) { _, optionValue ->
                undefinedMacros += optionValue
            }
            .collectOptionValues(options = LANGUAGE_SWITCHES) { _, optionValue ->
                /*
                 * `-x none` has a special meaning.
                 */
                if (optionValue != "none") {
                    language = Language(optionValue)
                }
            }
            .collectOptionValues("-o") { _, _ ->
                /*
                 * Ignore `-o` and its argument.
                 */
            }
            .toList()

        return ParsedCompilerCommand(
            projectRoot = projectRoot,
            directory = command.directory,
            file = command.file,
            compiler = compiler,
            language = language ?: command.file.language,
            includePaths = includePaths,
            definedMacros = definedMacros,
            undefinedMacros = undefinedMacros,
            arguments = arguments,
            ignoredArguments = ignoredArguments,
            parseErrors = parseErrors,
        )
    }

    private fun expandResponseFile(
        projectRoot: Path,
        directory: EnvPath,
        responseFile: EnvPath,
    ): Result<Sequence<Arg>> =
        runCatching {
            /*
             * Resolve from right to left.
             */
            val resolved = (projectRoot / (directory / responseFile)).getOrElse { error ->
                /*
                 * We may fail resolving environment paths against the local path.
                 */
                return failure(error)
            }

            /*-
             * Parse the content of the response file with the command-line
             * parser passed as a constructor argument.
             *
             * The `trimEnd()` call is necessary, because the command may still
             * contain the trailing newline sequence.
             */
            this(resolved.readText().trimEnd()).asSequence()
        }

    private val EnvPath.language: Language
        get() =
            toLocalPath().getOrNull()?.language ?: UNKNOWN

    private companion object {
        /**
         * Arguments which start with this character are gcc/clang response
         * files.
         */
        private const val RESPONSE_FILE = '@'

        /**
         * See [3.16 Options for Directory Search](https://gcc.gnu.org/onlinedocs/gcc/Directory-Options.html)
         */
        private val INCLUDE_SWITCHES: Array<out String> = arrayOf(
            "-I",
            "-iquote",
            "-isystem",
            "-idirafter",
            "-include",
            "-imacros",
        )

        /**
         * See [3.16 Options for Directory Search](https://gcc.gnu.org/onlinedocs/gcc/Directory-Options.html)
         */
        private val IGNORED_INCLUDE_SWITCHES: Array<out String> = arrayOf(
            "-I-",
        )

        private val DEFINE_MACRO_SWITCHES: Array<out String> = arrayOf(
            "-D",
        )

        private val UNDEFINE_MACRO_SWITCHES: Array<out String> = arrayOf(
            "-U",
        )

        private val LANGUAGE_SWITCHES: Array<out String> = arrayOf(
            "-x",
        )

        /**
         * C extensions (not including the dot).
         */
        private val C_EXTENSIONS: Array<out String> = arrayOf(
            "c",
        )

        /**
         * C++ extensions (not including the dot).
         */
        private val CXX_EXTENSIONS: Array<out String> = arrayOf(
            "C",
            "cc",
            "cpp",
            "cxx",
        )

        private val Arg.isResponseFile: Boolean
            get() =
                isNotEmpty() && this[0] == RESPONSE_FILE

        private val Throwable.localizedMessageExt: String?
            get() {
                val message = localizedMessage
                return when (this) {
                    is NoSuchFileException -> "No such file: $message"
                    else -> message
                }
            }

        private val Path.language: Language
            get() =
                when (val extension = extension) {
                    in C_EXTENSIONS -> C
                    in CXX_EXTENSIONS -> Cxx
                    "" -> UNKNOWN
                    else -> Language(extension)
                }

        private fun Sequence<Arg>.collectOptionValues(
            vararg options: String,
            consumeOptionAndValue: (String, String) -> Unit,
        ): Sequence<Arg> =
            sequence {
                fun Arg.isExpectedOption(): Boolean =
                    options.any(this::startsWith)

                /**
                 * @return the pair where the second value is the argument of
                 *   the switch, or `null` if there's no argument and the next
                 *   command-line argument should be examined instead.
                 */
                fun Arg.optionValueOrNull(): Pair<String, String?> =
                    options.asSequence()
                        .filter(this::startsWith)
                        .map { option ->
                            option to substring(option.length)
                        }
                        .map { (option, value) ->
                            option to value.nullIfEmpty()
                        }
                        .first()

                var lastOption: String? = null
                val expectingOptionValue: () -> Boolean = {
                    lastOption != null
                }

                forEach { arg ->
                    when {
                        /*
                         * The option value.
                         */
                        expectingOptionValue() -> {
                            consumeOptionAndValue(lastOption!!, arg)
                            lastOption = null
                        }

                        /*
                         * The expected option.
                         */
                        arg.isExpectedOption() -> {
                            val (option, value) = arg.optionValueOrNull()
                            when (value) {
                                null -> lastOption = option
                                else -> consumeOptionAndValue(option, value)
                            }
                        }

                        /*
                         * Not the the expected option, pass through.
                         */
                        else -> yield(arg)
                    }
                }
            }

        /**
         * - For both `DEBUG` and `DEBUG=1`, will return `("DEBUG", "1")`.
         * - For `DEBUG=`, will return  `("DEBUG", "")`.
         */
        private fun String.splitToNameAndValue(): Pair<String, String> =
            when (val index = indexOf('=')) {
                -1 -> this to "1"
                else -> substring(0, index) to substring(index + 1)
            }

        private fun String.nullIfEmpty(): String? =
            when {
                isEmpty() -> null
                else -> this
            }
    }
}
