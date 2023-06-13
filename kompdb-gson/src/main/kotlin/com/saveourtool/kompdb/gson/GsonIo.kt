package com.saveourtool.kompdb.gson

import com.saveourtool.kompdb.core.CompilationCommand
import com.saveourtool.kompdb.core.CompilationDatabase
import com.saveourtool.kompdb.core.JsonIo
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
