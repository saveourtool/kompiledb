package com.saveourtool.kompiledb.core.compiler.gcc

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import com.saveourtool.kompiledb.core.compiler.CompilerCommandParser
import com.saveourtool.kompiledb.core.compiler.ParsedCompilerCommand
import com.saveourtool.kompiledb.core.io.Arg
import com.saveourtool.kompiledb.core.io.CommandLineParser
import com.saveourtool.kompiledb.core.io.PathMapper
import com.saveourtool.kompiledb.core.io.PathMapperScope
import com.saveourtool.kompiledb.core.lang.Language
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import kotlin.Result.Companion.failure
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
            .collectIncludePathsTo(includePaths)
            .collectDefinedMacrosTo(definedMacros)
            .collectUndefinedMacrosTo(undefinedMacros)
            .collectLanguage {
                language = it
            }
            .toList()

        return ParsedCompilerCommand(
            projectRoot = projectRoot,
            directory = command.directory,
            file = command.file,
            compiler = compiler,
            language = language ?: Language("c++"),
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

        private val Arg.isResponseFile: Boolean
            get() =
                isNotEmpty() && this[0] == RESPONSE_FILE

        private val Arg.isIncludeSwitch: Boolean
            get() =
                INCLUDE_SWITCHES.any(this::startsWith) && !isIgnoredIncludeSwitch

        private val Arg.isIgnoredIncludeSwitch: Boolean
            get() =
                this in IGNORED_INCLUDE_SWITCHES

        private val Arg.isDefineMacroSwitch: Boolean
            get() =
                DEFINE_MACRO_SWITCHES.any(this::startsWith)

        private val Arg.isUndefineMacroSwitch: Boolean
            get() =
                UNDEFINE_MACRO_SWITCHES.any(this::startsWith)

        private val Arg.isLanguageSwitch: Boolean
            get() =
                LANGUAGE_SWITCHES.any(this::startsWith)

        private val Throwable.localizedMessageExt: String?
            get() {
                val message = localizedMessage
                return when (this) {
                    is NoSuchFileException -> "No such file: $message"
                    else -> message
                }
            }

        private fun Sequence<Arg>.collectIncludePathsTo(includePaths: MutableMap<String, MutableList<EnvPath>>): Sequence<Arg> =
            sequence {
                var lastIncludeSwitch: String? = null
                val expectingOptionArg: () -> Boolean = {
                    lastIncludeSwitch != null
                }

                forEach { argument ->
                    when {
                        expectingOptionArg() -> {
                            includePaths.compute(lastIncludeSwitch!!) { _, value ->
                                (value ?: mutableListOf()).apply {
                                    this += EnvPath(argument)
                                }
                            }
                            lastIncludeSwitch = null
                        }

                        /*
                         * -I
                         */
                        argument.isIncludeSwitch -> {
                            val (switch, includePath) = argument.includePathOrNull()
                            when (includePath) {
                                null -> lastIncludeSwitch = switch
                                else -> {
                                    includePaths.compute(switch) { _, value ->
                                        (value ?: mutableListOf()).apply {
                                            this += EnvPath(includePath)
                                        }
                                    }
                                }
                            }
                        }

                        /*
                         * Not an include path, pass through.
                         */
                        else -> yield(argument)
                    }
                }
            }

        private fun Sequence<Arg>.collectDefinedMacrosTo(definedMacros: MutableMap<String, String>): Sequence<Arg> =
            sequence {
                var expectingOptionArg = false

                forEach { argument ->
                    when {
                        expectingOptionArg -> {
                            val (name, value) = argument.splitToNameAndValue()
                            definedMacros[name] = value
                            expectingOptionArg = false
                        }

                        /*
                         * -D
                         */
                        argument.isDefineMacroSwitch -> {
                            when (val definedMacro = argument.definedMacroOrNull()) {
                                null -> expectingOptionArg = true
                                else -> {
                                    val (name, value) = definedMacro.splitToNameAndValue()
                                    definedMacros[name] = value
                                }
                            }
                        }

                        /*
                         * Not a `-D`, pass through.
                         */
                        else -> yield(argument)
                    }
                }
            }

        private fun Sequence<Arg>.collectUndefinedMacrosTo(undefinedMacros: MutableList<String>): Sequence<Arg> =
            sequence {
                var expectingOptionArg = false

                forEach { argument ->
                    when {
                        expectingOptionArg -> {
                            undefinedMacros += argument
                            expectingOptionArg = false
                        }

                        /*
                         * -U
                         */
                        argument.isUndefineMacroSwitch -> {
                            when (val undefinedMacro = argument.undefinedMacroOrNull()) {
                                null -> expectingOptionArg = true
                                else -> undefinedMacros += undefinedMacro
                            }
                        }

                        /*
                         * Not a `-U`, pass through.
                         */
                        else -> yield(argument)
                    }
                }
            }

        private fun Sequence<Arg>.collectLanguage(languageConsumer: (Language) -> Unit): Sequence<Arg> =
            sequence {
                var expectingOptionArg = false

                forEach { argument ->
                    when {
                        expectingOptionArg -> {
                            languageConsumer(Language(argument))
                            expectingOptionArg = false
                        }

                        /*
                         * -x
                         */
                        argument.isLanguageSwitch -> {
                            when (val language = argument.languageOrNull()) {
                                null -> expectingOptionArg = true
                                else -> languageConsumer(Language(language))
                            }
                        }

                        /*
                         * Not a `-x`, pass through.
                         */
                        else -> yield(argument)
                    }
                }
            }

        /**
         * @return the pair where the second value is the argument of the `-I`
         *   switch, or `null` if there's no argument and the next command-line
         *   argument should be examined instead.
         */
        private fun Arg.includePathOrNull(): Pair<String, String?> {
            require(isIncludeSwitch) {
                "Not an include (-I) switch: $this"
            }

            return INCLUDE_SWITCHES.asSequence()
                .filter(this::startsWith)
                .map { switch ->
                    switch to substring(switch.length)
                }
                .map { (switch, includePath) ->
                    switch to includePath.nullIfEmpty()
                }
                .first()
        }

        private fun Arg.definedMacroOrNull(): String? {
            require(isDefineMacroSwitch) {
                "Not a define macro (-D) switch: $this"
            }

            return DEFINE_MACRO_SWITCHES.asSequence()
                .filter(this::startsWith)
                .map { switch ->
                    substring(switch.length)
                }
                .map { definedMacro ->
                    definedMacro.nullIfEmpty()
                }
                .first()
        }

        private fun Arg.undefinedMacroOrNull(): String? {
            require(isUndefineMacroSwitch) {
                "Not an undefine macro (-U) switch: $this"
            }

            return UNDEFINE_MACRO_SWITCHES.asSequence()
                .filter(this::startsWith)
                .map { switch ->
                    substring(switch.length)
                }
                .map { undefinedMacro ->
                    undefinedMacro.nullIfEmpty()
                }
                .first()
        }

        private fun Arg.languageOrNull(): String? {
            require(isLanguageSwitch) {
                "Not a language (-x) switch: $this"
            }

            return LANGUAGE_SWITCHES.asSequence()
                .filter(this::startsWith)
                .map { switch ->
                    substring(switch.length)
                }
                .map { undefinedMacro ->
                    undefinedMacro.nullIfEmpty()
                }
                .first()
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
