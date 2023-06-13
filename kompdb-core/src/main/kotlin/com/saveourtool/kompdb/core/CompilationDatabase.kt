package com.saveourtool.kompdb.core

/**
 * A [_JSON Compilation Database_](https://clang.llvm.org/docs/JSONCompilationDatabase.html).
 *
 * @param commands the list of compilation commands.
 */
data class CompilationDatabase(
    val commands: List<CompilationCommand>
) {
    /**
     * @param commands the list of compilation commands.
     */
    constructor(vararg commands: CompilationCommand) : this(commands.toList())
}
