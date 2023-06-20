package com.saveourtool.kompiledb.core.io

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.io.parsers.PosixShellCommandLineParser
import com.saveourtool.kompiledb.core.io.parsers.WindowsCommandLineParser

interface CommandLineParser : (RawCommandLine) -> ParsedArgs {
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
