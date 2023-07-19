package com.saveourtool.kompiledb.jackson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import kotlin.collections.Map.Entry

internal object CompilationCommandDeserializer : JsonDeserializer<CompilationCommand>() {
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
        p: JsonParser,
        ctxt: DeserializationContext,
    ): CompilationCommand {
        val json = p.readValueAsTree<ObjectNode>()

        val props: Set<Entry<String, JsonNode>> = json.properties()

        val unexpectedFields = props.asSequence().map(Entry<String, *>::key).toSet() - FIELDS
        return when {
            unexpectedFields.isEmpty() -> {
                val directory: TextNode = json[DIRECTORY] as TextNode? ?: throwMismatchedInput(p, DIRECTORY)
                val file: TextNode = json[FILE] as TextNode? ?: throwMismatchedInput(p, FILE)
                val arguments: ArrayNode? = json[ARGUMENTS] as ArrayNode?
                val command: TextNode? = json[COMMAND] as TextNode?
                val output: TextNode? = json[OUTPUT] as TextNode?
                TODO("Not implemented")
            }

            else -> throwMismatchedInput(
                p,
                "${unexpectedFields.size} unexpected field(s) encountered (${unexpectedFields.joinToString()})",
            )
        }
    }

    private fun throwMismatchedInput(p: JsonParser, message: String): Nothing =
        throw MismatchedInputException.from(
            p,
            CompilationCommand::class.java,
            message,
        )
}
