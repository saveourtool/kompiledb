package com.saveourtool.kompiledb.jackson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.CompilationDatabase
import com.saveourtool.kompiledb.core.CompilationDatabase.Companion.COMPILE_COMMANDS_JSON
import com.saveourtool.kompiledb.core.EnvPath
import com.saveourtool.kompiledb.core.JsonIo
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.core.JacksonException
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.json.JsonMapper.Builder
import com.fasterxml.jackson.module.kotlin.KotlinFeature.StrictNullChecks
import com.fasterxml.jackson.module.kotlin.addDeserializer
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
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

internal class JacksonIo(
    initBuilder: Builder.() -> Unit,
    initMapper: JsonMapper.() -> Unit,
) : JsonIo {
    private val mapper = jsonMapper {
        initBuilder()

        kotlinModule {
            enable(StrictNullChecks)
        }
            .addDeserializer(EnvPath::class, EnvPathDeserializer)
            .addDeserializer(CompilationCommand::class, CompilationCommandDeserializer)
            .addDeserializer(CompilationDatabase::class, CompilationDatabaseDeserializer)
            .let(this::addModule)
    }
        .apply(initMapper)
        .setSerializationInclusion(NON_NULL)

    override fun CompilationDatabase.toJson(): String =
        mapper.writeValueAsString(this)

    override fun CompilationCommand.toJson(): String =
        mapper.writeValueAsString(this)

    override fun String.toCompilationCommand(): Result<CompilationCommand> =
        runCatching {
            mapper.readValue(this)
        }

    override fun String.toCompilationDatabase(): Result<CompilationDatabase> =
        runCatching {
            mapper.readValue(this)
        }

    override fun Reader.readCompilationDatabase(): Result<CompilationDatabase> =
        try {
            success(mapper.readValue(this))
        } catch (je: JacksonException) {
            when (val cause = je.cause) {
                is IOException -> throw cause
                else -> failure(je)
            }
        } catch (ioe: IOException) {
            throw ioe
        }

    override fun Path.readCompilationDatabase(charset: Charset): Result<CompilationDatabase> =
        when {
            isDirectory() && name != COMPILE_COMMANDS_JSON -> (this / COMPILE_COMMANDS_JSON).readCompilationDatabase(charset)

            else -> bufferedReader(charset).use { reader ->
                reader.readCompilationDatabase()
            }
        }
}
