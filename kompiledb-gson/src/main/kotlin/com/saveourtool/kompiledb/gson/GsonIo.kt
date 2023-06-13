package com.saveourtool.kompiledb.gson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.CompilationDatabase
import com.saveourtool.kompiledb.core.JsonIo
import com.google.gson.Gson
import com.google.gson.GsonBuilder

internal class GsonIo(init: GsonBuilder.() -> Unit) : JsonIo {
    private val gson: Gson = GsonBuilder()
        .apply(init)
        .registerTypeAdapter(CompilationDatabase::class.java, CompilationDatabaseAdapter())
        .create()

    override fun CompilationDatabase.toJson(): String =
        gson.toJson(this)

    override fun CompilationCommand.toJson(): String =
        gson.toJson(this)
}
