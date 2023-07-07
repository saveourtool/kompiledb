package com.saveourtool.kompiledb.core.compiler

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.compiler.clang.ClangCommandParser
import com.saveourtool.kompiledb.core.io.CommandLineParser
import com.saveourtool.kompiledb.core.io.PathMapper
import com.saveourtool.kompiledb.core.io.mappers.LocalPathMapper
import java.nio.file.Path

/**
 * Parses a [CompilationCommand] in a compiler-specific way.
 */
fun interface CompilerCommandParser {
    /**
     * Parses the compilation command.
     *
     * @param databasePath the path to the compilation database JSON file (or
     *   its parent directory) on the local file system.
     * @param command the compilation command to parse.
     * @return the parsed command.
     */
    fun parse(
        databasePath: Path,
        command: CompilationCommand,
    ): ParsedCompilerCommand

    companion object {
        /**
         * Creates the default compiler command parser.
         */
        operator fun invoke(
            pathMapper: PathMapper = LocalPathMapper,
            commandLineParser: CommandLineParser = CommandLineParser(),
        ): CompilerCommandParser =
            ClangCommandParser(pathMapper, commandLineParser)
    }
}
