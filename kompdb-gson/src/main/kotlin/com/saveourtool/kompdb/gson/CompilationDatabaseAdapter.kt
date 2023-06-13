package com.saveourtool.kompdb.gson

import com.saveourtool.kompdb.core.CompilationCommand
import com.saveourtool.kompdb.core.CompilationDatabase
import com.google.gson.JsonArray
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

internal class CompilationDatabaseAdapter : JsonSerializer<CompilationDatabase> {
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
}
