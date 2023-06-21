package com.saveourtool.kompiledb.gson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.CompilationDatabase
import com.saveourtool.kompiledb.core.CompilationDatabase.Companion.COMPILE_COMMANDS_JSON
import com.saveourtool.kompiledb.core.JsonIo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.io.Reader
import java.nio.charset.Charset
import java.nio.file.Path
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success
import kotlin.io.path.bufferedReader
import kotlin.io.path.div
import kotlin.io.path.isDirectory
import kotlin.io.path.name

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
        try {
            success(gson.fromJson(this, CompilationDatabase::class.java))
        } catch (ioe: IOException) {
            throw ioe
        } catch (jse: JsonSyntaxException) {
            when (val cause = jse.cause) {
                is IOException -> throw cause
                else -> failure(jse)
            }
        }

    override fun Path.readCompilationDatabase(charset: Charset): Result<CompilationDatabase> =
        when {
            isDirectory() && name != COMPILE_COMMANDS_JSON -> (this / COMPILE_COMMANDS_JSON).readCompilationDatabase(charset)

            else -> bufferedReader(charset).use { reader ->
                reader.readCompilationDatabase()
            }
        }
}
