package com.saveourtool.kompiledb.gson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.CompilationDatabase
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

internal object CompilationDatabaseAdapter :
    JsonSerializer<CompilationDatabase>,
    JsonDeserializer<CompilationDatabase> {
    override fun serialize(
        src: CompilationDatabase,
        typeOfSrc: Type,
        context: JsonSerializationContext,
    ): JsonArray =
        JsonArray().apply {
            src.commands.asSequence()
                .map { command ->
                    context.serialize(command, CompilationCommand::class.java)
                }
                .forEach(this::add)
        }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): CompilationDatabase =
        when (json) {
            is JsonArray -> {
                val errors = mutableMapOf<Int, String>()

                val commands = json.asSequence()
                    .map { command ->
                        runCatching {
                            context.deserialize<CompilationCommand>(command, CompilationCommand::class.java)
                        }
                    }
                    .onEachIndexed { index, result ->
                        val error = result.exceptionOrNull()
                        if (error != null) {
                            errors[index] = error.message ?: error.toString()
                        }
                    }
                    .map(Result<CompilationCommand>::getOrNull)
                    .filterNotNull()
                    .toList()

                CompilationDatabase(commands, errors)
            }

            else -> throw JsonSyntaxException("When reading a database, expected a JSON array but was a ${json.javaClass.simpleName}: $json")
        }
}
