package com.saveourtool.kompiledb.core.compiler.gcc

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import com.saveourtool.kompiledb.core.compiler.CompilerCommandParser
import com.saveourtool.kompiledb.core.compiler.ParsedCompilerCommand
import com.saveourtool.kompiledb.core.io.Arg
import com.saveourtool.kompiledb.core.io.CommandLineParser
import com.saveourtool.kompiledb.core.io.PathMapper
import com.saveourtool.kompiledb.core.io.PathMapperScope
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
        val projectDir = when {
            databasePath.isDirectory() -> databasePath

            else -> {
                val parentDir = databasePath.parent
                require(parentDir != null) {
                    "Database file $databasePath doesn't have a parent"
                }
                parentDir
            }
        }

        val errors = mutableListOf<String>()
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
                        projectDir,
                        command.directory,
                        EnvPath(argument.substring(1)),
                    )
                        .getOrElse { failure ->
                            /*
                             * Collect errors, if any.
                             */
                            errors += failure.localizedMessageExt ?: failure.toString()
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
                errors += "The compiler path is empty"
                EnvPath.EMPTY
            }

        var lastIncludeSwitch: String? = null
        val expectingIncludePath: () -> Boolean = {
            lastIncludeSwitch != null
        }
        var expectingDefinedMacro = false
        var expectingUndefinedMacro = false
        val includePaths = mutableMapOf<String, EnvPath>()
        val definedMacros = mutableMapOf<String, String>()
        val undefinedMacros = mutableListOf<String>()

        val ignoredArguments = arguments.asSequence()
            .drop(1)
            .filter { argument ->
                when {
                    expectingIncludePath() -> {
                        includePaths[lastIncludeSwitch!!] = EnvPath(argument)
                        lastIncludeSwitch = null
                        false
                    }

                    expectingDefinedMacro -> {
                        val (name, value) = argument.splitToNameAndValue()
                        definedMacros[name] = value
                        expectingDefinedMacro = false
                        false

                    }

                    expectingUndefinedMacro -> {
                        undefinedMacros += argument
                        expectingUndefinedMacro = false
                        false
                    }

                    /*
                     * -I
                     */
                    argument.isIncludeSwitch -> {
                        val (switch, includePath) = argument.includePathOrNull()
                        when (includePath) {
                            null -> lastIncludeSwitch = switch
                            else -> includePaths[switch] = EnvPath(includePath)
                        }
                        false
                    }

                    /*
                     * -D
                     */
                    argument.isDefineMacroSwitch -> {
                        when (val definedMacro = argument.definedMacroOrNull()) {
                            null -> expectingDefinedMacro = true
                            else -> {
                                val (name, value) = definedMacro.splitToNameAndValue()
                                definedMacros[name] = value
                            }
                        }
                        false
                    }

                    /*
                     * -U
                     */
                    argument.isUndefineMacroSwitch -> {
                        when (val undefinedMacro = argument.undefinedMacroOrNull()) {
                            null -> expectingUndefinedMacro = true
                            else -> undefinedMacros += undefinedMacro
                        }
                        false
                    }

                    /*
                     * Neither an include path nor `-D`/`-U`.
                     */
                    else -> true
                }
            }
            .toList()

        return ParsedCompilerCommand(
            compiler = compiler,
            includePaths = includePaths,
            definedMacros = definedMacros,
            undefinedMacros = undefinedMacros,
            arguments = arguments,
            ignoredArguments = ignoredArguments,
            errors = errors,
        )
    }

    private fun expandResponseFile(
        projectDir: Path,
        directory: EnvPath,
        responseFile: EnvPath,
    ): Result<Sequence<Arg>> =
        runCatching {
            /*
             * Resolve from right to left.
             */
            val resolved = (projectDir / (directory / responseFile)).getOrElse { error ->
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

        private val Throwable.localizedMessageExt: String?
            get() {
                val message = localizedMessage
                return when (this) {
                    is NoSuchFileException -> "No such file: $message"
                    else -> message
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

        /**
         * - For both `DEBUG` and `DEBUG=1`, will return `("DEBUG", "1")`.
         * - For `DEBUG=`, will return  `("DEBUG", "")`.
         */
        private fun String.splitToNameAndValue(): Pair<String, String> =
            when (val index = indexOf('=')) {
                -1 -> this to "1"
                else -> substring(0, index) to substring(index + 1)
            }

        private fun String.nullIfEmpty(): String? = when {
            isEmpty() -> null
            else -> this
        }
    }
}
