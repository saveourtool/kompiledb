@file:JvmName("JsonIoExtensionsGson")

package com.saveourtool.kompdb.core

import com.saveourtool.kompdb.core.JsonIo.Factory
import com.saveourtool.kompdb.gson.GsonIo
import com.google.gson.GsonBuilder

/**
 * Creates a [JsonIo] instance which uses _Google Gson_ to read and write JSON.
 */
val Factory.gson: JsonIo
    get() =
        gson()

/**
 * Creates a [JsonIo] instance which uses _Google Gson_ to read and write JSON.
 *
 * @param init the optional configuration.
 */
fun Factory.gson(init: GsonBuilder.() -> Unit = {}): JsonIo =
    GsonIo(init)
