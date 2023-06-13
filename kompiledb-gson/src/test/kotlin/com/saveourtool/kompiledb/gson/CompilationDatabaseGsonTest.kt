package com.saveourtool.kompiledb.gson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.CompilationDatabase
import com.saveourtool.kompiledb.core.JsonIo
import com.saveourtool.kompiledb.core.gson
import io.kotest.assertions.json.shouldBeEmptyJsonArray
import io.kotest.assertions.json.shouldEqualJson
import org.intellij.lang.annotations.Language
import kotlin.test.Test
import com.saveourtool.kompiledb.core.EnvPath as Path

/**
 * @see CompilationDatabase
 */
class CompilationDatabaseGsonTest {
    private val jsonIo = JsonIo.gson {
        setPrettyPrinting()
    }

    @Test
    fun `single command should serialize successfully`() {
        val command = CompilationCommand(Path(""), Path("file.c"), listOf("gcc", "-c", "file.c"))

        val actualJson = with(jsonIo) {
            command.toJson()
        }

        @Language("JSON")
        val expectedJson = """
            {
              "directory": "",
              "file": "file.c",
              "arguments": [
                "gcc",
                "-c",
                "file.c"
              ]
            }
            """.trimIndent()

        actualJson shouldEqualJson expectedJson
    }

    @Test
    fun `simple database should serialize successfully`() {
        val command0 = CompilationCommand(Path(""), Path("file1.c"), listOf("gcc", "-c", "file1.c"))
        val command1 = CompilationCommand(Path(""), Path("file2.c"), listOf("gcc", "-c", "file2.c"))

        val database = CompilationDatabase(command0, command1)

        val actualJson = with(jsonIo) {
            database.toJson()
        }

        @Language("JSON")
        val expectedJson = """
            [
              {
                "directory": "",
                "file": "file1.c",
                "arguments": [
                  "gcc",
                  "-c",
                  "file1.c"
                ]
              },
              {
                "directory": "",
                "file": "file2.c",
                "arguments": [
                  "gcc",
                  "-c",
                  "file2.c"
                ]
              }
            ]
            """.trimIndent()

        actualJson shouldEqualJson expectedJson
    }

    @Test
    fun `empty database should serialize successfully`() {
        with(jsonIo) {
            CompilationDatabase().toJson()
        }.shouldBeEmptyJsonArray()
    }
}
