@file:JvmName("JsonIoExtensionsJackson")

package com.saveourtool.kompiledb.core

import com.saveourtool.kompiledb.core.JsonIo.Factory
import com.saveourtool.kompiledb.jackson.JacksonIo
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.json.JsonMapper.Builder

/**
 * Creates a [JsonIo] instance which uses _Jackson_ to read and write JSON.
 */
val Factory.jackson: JsonIo
    get() =
        jackson()

/**
 * Creates a [JsonIo] instance which uses _Jackson_ to read and write JSON.
 *
 * @param initBuilder the optional configuration for [Builder].
 * @param initMapper the optional configuration for [JsonMapper].
 */
fun Factory.jackson(
    initBuilder: Builder.() -> Unit = {},
    initMapper: JsonMapper.() -> Unit = {},
): JsonIo =
    JacksonIo(initBuilder, initMapper)
