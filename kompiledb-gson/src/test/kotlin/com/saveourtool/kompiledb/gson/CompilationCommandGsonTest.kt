package com.saveourtool.kompiledb.gson

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.JsonIo
import com.saveourtool.kompiledb.core.gson
import com.saveourtool.kompiledb.core.matchers.shouldBeCommand
import com.google.gson.JsonSyntaxException
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import org.intellij.lang.annotations.Language
import kotlin.test.Test
import com.saveourtool.kompiledb.core.EnvPath as Path

/**
 * @see CompilationCommand
 */
class CompilationCommandGsonTest {
    private val jsonIo = JsonIo.gson {
        setPrettyPrinting()
    }

    @Test
    fun `single command should serialize successfully`() {
        val command = CompilationCommand(Path(""), Path("file.c"), listOf("clang", "-c", "file.c"))

        val actualJson = with(jsonIo) {
            command.toJson()
        }

        @Language("JSON")
        val expectedJson = """
            {
              "directory": "",
              "file": "file.c",
              "arguments": [
                "clang",
                "-c",
                "file.c"
              ]
            }
            """.trimIndent()

        actualJson shouldEqualJson expectedJson
    }

    @Test
    fun `single command with arguments should be read successfully`() {
        @Language("JSON")
        val json = """
            {
                "directory": "",
                "file": "file.c",
                "arguments": [
                    "clang",
                    "-c",
                    "file.c"
                ]
            }
        """.trimIndent()

        val command = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeSuccess()

        command.shouldBeCommand(
            file = "file.c",
            arguments = listOf("clang", "-c", "file.c"),
        )
    }

    @Test
    fun `single command should be read successfully`() {
        @Language("JSON")
        val json = """
            {
                "directory": "C:/Users/alice/cmake-3.26.4/Source",
                "command": "/C/Program_Files/msys64/mingw64/bin/clang++.exe -DCURL_STATICLIB -DLIBARCHIVE_STATIC -DUNICODE -DWIN32_LEAN_AND_MEAN -D_UNICODE @CMakeFiles/CMakeLib.dir/includes_CXX.rsp -O3 -DNDEBUG -std=c++17 -o CMakeFiles/CMakeLib.dir/cmInstalledFile.cxx.obj -c /C/Users/alice/cmake-3.26.4/Source/cmInstalledFile.cxx",
                "file": "C:/Users/alice/cmake-3.26.4/Source/cmInstalledFile.cxx",
                "output": "Source/CMakeFiles/CMakeLib.dir/cmInstalledFile.cxx.obj"
            }
        """.trimIndent()

        val command = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeSuccess()

        command.shouldBeCommand(
            directory = "C:/Users/alice/cmake-3.26.4/Source",
            file = "C:/Users/alice/cmake-3.26.4/Source/cmInstalledFile.cxx",
            command = "/C/Program_Files/msys64/mingw64/bin/clang++.exe -DCURL_STATICLIB -DLIBARCHIVE_STATIC -DUNICODE -DWIN32_LEAN_AND_MEAN -D_UNICODE @CMakeFiles/CMakeLib.dir/includes_CXX.rsp -O3 -DNDEBUG -std=c++17 -o CMakeFiles/CMakeLib.dir/cmInstalledFile.cxx.obj -c /C/Users/alice/cmake-3.26.4/Source/cmInstalledFile.cxx",
            output = "Source/CMakeFiles/CMakeLib.dir/cmInstalledFile.cxx.obj",
        )
    }

    @Test
    fun `null argument entries when reading`() {
        @Language("JSON")
        val json = """
            {
                "directory": "",
                "file": "file.c",
                "arguments": [
                    null,
                    "file.c"
                ]
            }
        """.trimIndent()

        val failure = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeFailure<JsonSyntaxException>()

        failure.message.shouldNotBeNull() shouldBeEqual
                """`arguments[0]` is null: {"directory":"","file":"file.c","arguments":[null,"file.c"]}"""
    }

    @Test
    fun `non-string argument entries when reading`() {
        @Language("JSON")
        val json = """
            {
                "directory": "",
                "file": "file.c",
                "arguments": [
                    41,
                    42
                ]
            }
        """.trimIndent()

        val failure = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeFailure<JsonSyntaxException>()

        failure.message.shouldNotBeNull() shouldBeEqual
                """Expected `arguments[0]` to be a string but was a JsonPrimitive: {"directory":"","file":"file.c","arguments":[41,42]}"""
    }

    @Test
    fun `null fields should be read successfully`() {
        @Language("JSON")
        val json = """
            {
                "directory": "",
                "file": "file.c",
                "arguments": [
                    "clang",
                    "-c",
                    "file.c"
                ],
                "command": null,
                "output": null
            }
        """.trimIndent()

        val command = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeSuccess()

        command.shouldBeCommand(
            file = "file.c",
            arguments = listOf("clang", "-c", "file.c"),
        )
    }

    @Test
    fun `null directory when reading`() {
        @Language("JSON")
        val json = """
            {
                "directory": null,
                "file": "file.c",
                "arguments": [
                    "clang",
                    "-c",
                    "file.c"
                ]
            }
        """.trimIndent()

        val failure = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeFailure<JsonSyntaxException>()

        failure.message.shouldNotBeNull() shouldBeEqual
                """`directory` is null: {"directory":null,"file":"file.c","arguments":["clang","-c","file.c"]}"""
    }

    @Test
    fun `extra fields when reading`() {
        @Language("JSON")
        val json = """
            {
                "directory": "",
                "file": "file.c",
                "arguments": [
                    "clang",
                    "-c",
                    "file.c"
                ],
                "extra1": null,
                "extra2": "foo",
                "extra3": ["bar"],
                "extra4": {}
            }
        """.trimIndent()

        val failure = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeFailure<JsonSyntaxException>()

        failure.message.shouldNotBeNull() shouldBeEqual
                """4 unexpected field(s) encountered (extra1, extra2, extra3, extra4): {"directory":"","file":"file.c","arguments":["clang","-c","file.c"],"extra1":null,"extra2":"foo","extra3":["bar"],"extra4":{}}"""
    }

    @Test
    fun `empty object when reading`() {
        @Language("JSON")
        val json = """
            {
            }
        """.trimIndent()

        val failure = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeFailure<JsonSyntaxException>()

        failure.message.shouldNotBeNull() shouldBeEqual
                """`directory` is missing: {}"""
    }

    @Test
    fun `not an object when reading`() {
        @Language("JSON")
        val json = "[]"

        val failure = with(jsonIo) {
            json.toCompilationCommand()
        }.shouldBeFailure<JsonSyntaxException>()

        failure.message.shouldNotBeNull() shouldBeEqual
                "Expected a JSON object but was a JsonArray: []"
    }
}
