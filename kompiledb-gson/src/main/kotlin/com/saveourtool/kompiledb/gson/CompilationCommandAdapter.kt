package com.saveourtool.kompiledb.gson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

internal object CompilationCommandAdapter : JsonDeserializer<CompilationCommand> {
    private const val DIRECTORY = "directory"

    private const val FILE = "file"

    private const val ARGUMENTS = "arguments"

    private const val COMMAND = "command"

    private const val OUTPUT = "output"

    private val FIELDS: Array<out String> = arrayOf(
        DIRECTORY,
        FILE,
        ARGUMENTS,
        COMMAND,
        OUTPUT,
    )

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CompilationCommand =
        when (json) {
            is JsonObject -> with(json) {
                val unexpectedFields = keySet().minus(FIELDS)
                when {
                    unexpectedFields.isEmpty() -> CompilationCommand(directory, file, arguments, command, output)
                    else -> throw JsonSyntaxException("${unexpectedFields.size} unexpected field(s) encountered (${unexpectedFields.joinToString()}): $json")
                }
            }

            else -> throw JsonSyntaxException("Expected a JSON object but was a ${json.javaClass.simpleName}: $json")
        }

    private val JsonObject.directory: EnvPath
        get() =
            getAsJsonString(DIRECTORY).let(::EnvPath)

    private val JsonObject.file: EnvPath
        get() =
            getAsJsonString(FILE).let(::EnvPath)

    private val JsonObject.arguments: List<String>
        get() =
            getAsJsonStringArray(ARGUMENTS)

    private val JsonObject.command: String?
        get() =
            getAsJsonStringOrNull(COMMAND)

    private val JsonObject.output: EnvPath?
        get() =
            getAsJsonStringOrNull(OUTPUT)?.let(::EnvPath)

    private fun JsonObject.getAsJsonString(fieldName: String): String {
        val field = get(fieldName)

        return when {
            field == null -> throw JsonSyntaxException("`$fieldName` is missing: $this")
            field is JsonNull -> throw JsonSyntaxException("`$fieldName` is null: $this")
            field is JsonPrimitive && field.isString -> field.asString
            else -> throw JsonSyntaxException("Expected `$fieldName` to be a string but was a ${field.javaClass.simpleName}: $this")
        }
    }

    private fun JsonObject.getAsJsonStringOrNull(fieldName: String): String? {
        val field = get(fieldName)

        return when {
            field == null -> null
            field is JsonNull -> null
            field is JsonPrimitive && field.isString -> field.asString
            else -> throw JsonSyntaxException("Expected `$fieldName` to be a string but was a ${field.javaClass.simpleName}: $this")
        }
    }

    private fun JsonObject.getAsJsonStringArray(fieldName: String): List<String> =
        when (val field = get(fieldName)) {
            null -> emptyList()
            is JsonNull -> emptyList()
            is JsonArray -> field.asSequence().mapIndexed { index, entry ->
                entry.getAsJsonString(fieldName, index, this)
            }.toList()

            else -> throw JsonSyntaxException("Expected `$fieldName` to be a string but was a ${field.javaClass.simpleName}: $this")
        }

    private fun JsonElement.getAsJsonString(fieldName: String, index: Int, jsonObject: JsonObject): String =
        when {
            this is JsonNull -> throw JsonSyntaxException("`$fieldName[$index]` is null: $jsonObject")
            this is JsonPrimitive && isString -> asString
            else -> throw JsonSyntaxException("Expected `$fieldName[$index]` to be a string but was a ${javaClass.simpleName}: $jsonObject")
        }
}
