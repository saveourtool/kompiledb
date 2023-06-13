package com.saveourtool.kompiledb.core

import org.intellij.lang.annotations.Language

interface JsonIo {
    /**
     * Converts this compilation database to JSON.
     *
     * @see CompilationCommand.toJson
     */
    @Language("JSON")
    fun CompilationDatabase.toJson(): String

    /**
     * Converts this compilation command to JSON.
     *
     * @see CompilationDatabase.toJson
     */
    @Language("JSON")
    fun CompilationCommand.toJson(): String

    /**
     * The factory used to create instances of [JsonIo].
     */
    companion object Factory
}
