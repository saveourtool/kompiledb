package com.saveourtool.kompiledb.jackson

import com.saveourtool.kompiledb.core.CompilationDatabase
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

internal object CompilationDatabaseDeserializer : JsonDeserializer<CompilationDatabase>() {
    override fun deserialize(
        p: JsonParser,
        ctxt: DeserializationContext,
    ): CompilationDatabase {
        TODO("")
    }
}
