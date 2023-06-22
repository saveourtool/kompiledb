package com.saveourtool.kompiledb.core.io

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.io.parsers.PosixShellCommandLineParser
import com.saveourtool.kompiledb.core.io.parsers.WindowsCommandLineParser

/**
 * Command-line parser.
 *
 * It can't handle multiple commands separated with `&`, `&&`, `|`, `||` etc.,
 * so any multi-line batch file code should be previously split into individual
 * commands.
 *
 * @see CompilationCommand.parsedArguments
 */
interface CommandLineParser : (RawCommandLine) -> ParsedArgs {
    /**
     * Splits the receiver into individual arguments.
     *
     * @return the compile command `argv` as a list of strings, in a form ready
     *   to be passed to `execvp()` (i.e. un-escaped).
     * @see CompilationCommand.arguments
     */
    val CompilationCommand.parsedArguments: ParsedArgs
        get() =
            when (val command = command) {
                null -> arguments
                else -> invoke(command)
            }

    companion object {
        /**
         * @return the default system-dependent command-line parser.
         * @see WindowsCommandLineParser
         * @see PosixShellCommandLineParser
         */
        operator fun invoke(): CommandLineParser =
            when {
                System.getProperty("os.name").startsWith("Windows ") -> WindowsCommandLineParser
                else -> PosixShellCommandLineParser
            }
    }
}
