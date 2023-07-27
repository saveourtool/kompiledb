package com.saveourtool.kompiledb.jackson

import com.saveourtool.kompiledb.core.EnvPath
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.TextNode

internal object EnvPathDeserializer : JsonDeserializer<EnvPath>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): EnvPath =
        p.readValueAsTree<TextNode>().textValue().let(::EnvPath)
}
