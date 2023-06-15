package com.saveourtool.kompiledb.gson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.CompilationDatabase
import com.saveourtool.kompiledb.core.JsonIo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Reader
import java.nio.charset.Charset
import java.nio.file.Path
import kotlin.io.path.bufferedReader

internal class GsonIo(init: GsonBuilder.() -> Unit) : JsonIo {
    private val gson: Gson = GsonBuilder()
        .apply(init)
        .registerTypeAdapter(CompilationDatabase::class.java, CompilationDatabaseAdapter)
        .registerTypeAdapter(CompilationCommand::class.java, CompilationCommandAdapter)
        .disableJdkUnsafe()
        .create()

    override fun CompilationDatabase.toJson(): String =
        gson.toJson(this)

    override fun CompilationCommand.toJson(): String =
        gson.toJson(this)

    override fun String.toCompilationCommand(): Result<CompilationCommand> =
        runCatching {
            gson.fromJson(this, CompilationCommand::class.java)
        }

    override fun String.toCompilationDatabase(): Result<CompilationDatabase> =
        runCatching {
            gson.fromJson(this, CompilationDatabase::class.java)
        }

    override fun Reader.readCompilationDatabase(): Result<CompilationDatabase> =
        runCatching {
            gson.fromJson(this, CompilationDatabase::class.java)
        }

    override fun Path.readCompilationDatabase(charset: Charset): Result<CompilationDatabase> =
        bufferedReader(charset).use { reader ->
            reader.readCompilationDatabase()
        }
}
