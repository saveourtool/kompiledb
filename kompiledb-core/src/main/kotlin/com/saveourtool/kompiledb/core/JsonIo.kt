package com.saveourtool.kompiledb.core

import org.intellij.lang.annotations.Language
import java.io.IOException
import java.io.Reader
import java.nio.charset.Charset
import java.nio.file.Path
import kotlin.jvm.Throws
import kotlin.text.Charsets.UTF_8

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
     * De-serializes a compilation command from this JSON string.
     *
     * @return either a compilation command, or a JSON syntax error.
     * @see String.toCompilationDatabase
     */
    fun String.toCompilationCommand(): Result<CompilationCommand>

    /**
     * De-serializes a compilation database from this JSON string.
     *
     * @return either a compilation database, or a JSON syntax error.
     * @see String.toCompilationCommand
     */
    fun String.toCompilationDatabase(): Result<CompilationDatabase>

    /**
     * Reads the content of a character stream as a compilation database.
     *
     * **Note**:  It is the caller's responsibility to close this reader.
     *
     * @return either a compilation database, or a JSON syntax error.
     * @throws IOException in case an I/O error occurs while reading the content
     *   of a stream.
     */
    @Throws(IOException::class)
    fun Reader.readCompilationDatabase(): Result<CompilationDatabase>

    /**
     * Reads the content of a JSON file as a compilation database.
     *
     * @return either a compilation database, or a JSON syntax error.
     * @throws IOException in case an I/O error occurs while reading a file.
     */
    @Throws(IOException::class)
    fun Path.readCompilationDatabase(charset: Charset = UTF_8): Result<CompilationDatabase>

    /**
     * The factory used to create instances of [JsonIo].
     */
    companion object Factory
}
