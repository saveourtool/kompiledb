package com.saveourtool.kompiledb.core

/**
 * A [_JSON Compilation Database_](https://clang.llvm.org/docs/JSONCompilationDatabase.html).
 *
 * @param commands the list of compilation commands.
 * @param errors the errors encountered when reading an external file (not a
 *   part of the standard, not serialized to JSON).
 */
data class CompilationDatabase(
    val commands: List<CompilationCommand>,
    val errors: Map<Int, String> = emptyMap(),
) {
    /**
     * @param commands the list of compilation commands.
     */
    constructor(vararg commands: CompilationCommand) : this(commands.toList())
}
