package com.saveourtool.kompiledb.gson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.CompilationDatabase
import com.saveourtool.kompiledb.core.JsonIo
import com.saveourtool.kompiledb.core.gson
import com.google.gson.JsonSyntaxException
import io.kotest.assertions.json.shouldBeEmptyJsonArray
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
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

    @Test
    fun `empty database should be read successfully`() {
        @Language("JSON")
        val json = "[]"

        val database = with(jsonIo) {
            json.toCompilationDatabase()
        }.shouldBeSuccess()

        database.commands.shouldBeEmpty()
    }

    @Test
    fun `not an array when reading a database`() {
        @Language("JSON")
        val json = """
            {
                "foo": 42,
                "bar": null
            }
        """.trimIndent()

        val failure = with(jsonIo) {
            json.toCompilationDatabase()
        }.shouldBeFailure<JsonSyntaxException>()

        failure.message.shouldNotBeNull() shouldBeEqual
                """When reading a database, expected a JSON array but was a JsonObject: {"foo":42,"bar":null}"""
    }

    @Test
    fun `mixed database should be read partially`() {
        @Language("JSON")
        val json = """
            [
                {
                    "directory": "C:/Users/alice/cmake-3.26.4/Source",
                    "file": "C:/Users/alice/cmake-3.26.4/Source/cmInstalledFile.cxx",
                    "command": "/C/Program_Files/msys64/mingw64/bin/g++.exe -DCURL_STATICLIB -DLIBARCHIVE_STATIC -DUNICODE -DWIN32_LEAN_AND_MEAN -D_UNICODE @CMakeFiles/CMakeLib.dir/includes_CXX.rsp -O3 -DNDEBUG -std=c++17 -o CMakeFiles/CMakeLib.dir/cmInstalledFile.cxx.obj -c /C/Users/alice/cmake-3.26.4/Source/cmInstalledFile.cxx",
                    "output": "Source/CMakeFiles/CMakeLib.dir/cmInstalledFile.cxx.obj"
                },
                {
                    "directory": "C:/Users/alice/cmake-3.26.4/Source",
                    "file": "C:/Users/alice/cmake-3.26.4/Source/cmMarkAsAdvancedCommand.cxx",
                    "output": "Source/CMakeFiles/CMakeLib.dir/cmMarkAsAdvancedCommand.cxx.obj"
                },
                {
                    "directory": "C:/Users/alice/cmake-3.26.4/Source",
                    "file": "C:/Users/alice/cmake-3.26.4/Source/cmFileAPIToolchains.cxx",
                    "arguments": [
                        "/C/Program_Files/msys64/mingw64/bin/g++.exe",
                        41,
                        42,
                        null
                    ],
                    "output": "Source/CMakeFiles/CMakeLib.dir/cmFileAPIToolchains.cxx.obj"
                },
                {
                    "directory": "C:/Users/alice/cmake-3.26.4/Source",
                    "file": "C:/Users/alice/cmake-3.26.4/Source/cmInstallFileSetGenerator.cxx",
                    "command": "/C/Program_Files/msys64/mingw64/bin/g++.exe -DCURL_STATICLIB -DLIBARCHIVE_STATIC -DUNICODE -DWIN32_LEAN_AND_MEAN -D_UNICODE @CMakeFiles/CMakeLib.dir/includes_CXX.rsp -O3 -DNDEBUG -std=c++17 -o CMakeFiles/CMakeLib.dir/cmInstallFileSetGenerator.cxx.obj -c /C/Users/alice/cmake-3.26.4/Source/cmInstallFileSetGenerator.cxx",
                    "output": "Source/CMakeFiles/CMakeLib.dir/cmInstallFileSetGenerator.cxx.obj"
                }
            ]
            """.trimIndent()

        val database = with(jsonIo) {
            json.toCompilationDatabase()
        }.shouldBeSuccess()

        database.commands shouldHaveSize 2
        database.errors shouldHaveSize 2

        database.errors[1].shouldNotBeNull() shouldBeEqual
                "Either `arguments` or `command` is required"
        database.errors[2].shouldNotBeNull() shouldBeEqual
                """Expected `arguments[1]` to be a string but was a JsonPrimitive: {"directory":"C:/Users/alice/cmake-3.26.4/Source","file":"C:/Users/alice/cmake-3.26.4/Source/cmFileAPIToolchains.cxx","arguments":["/C/Program_Files/msys64/mingw64/bin/g++.exe",41,42,null],"output":"Source/CMakeFiles/CMakeLib.dir/cmFileAPIToolchains.cxx.obj"}"""
    }
}
