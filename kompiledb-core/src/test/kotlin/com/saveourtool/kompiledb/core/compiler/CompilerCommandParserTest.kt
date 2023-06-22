package com.saveourtool.kompiledb.core.compiler

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldStartWith
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.div
import kotlin.io.path.name
import kotlin.io.path.writeText
import kotlin.test.Test

/**
 * @see CompilerCommandParser
 */
class CompilerCommandParserTest {
    @Test
    fun `response file should get expanded (arguments)`(@TempDir projectDir: Path) {
        val sourceDir = (projectDir / "src").createDirectories()

        val responseFile = (sourceDir / "file.rsp").apply {
            writeText("-isystemfoo -Ibar -I baz")
        }

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf("gcc", "@${responseFile.name}", "-c", "file.c", "-o", "file.o"),
            output = EnvPath("file.o")
        )

        val parsedCommand= CompilerCommandParser().parse(projectDir, command)

        parsedCommand.errors.shouldBeEmpty()
        parsedCommand.arguments.shouldContainInOrder(
            "gcc",
            "-isystemfoo",
            "-Ibar",
            "-I",
            "baz",
            "-c",
            "file.c",
            "-o",
            "file.o",
        )
    }

    @Test
    fun `response file should get expanded (command)`(@TempDir projectDir: Path) {
        val sourceDir = (projectDir / "src").createDirectories()

        val responseFile = (sourceDir / "file.rsp").apply {
            writeText("-isystemfoo -Ibar -I baz")
        }

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            command = "gcc @${responseFile.name} -c file.c -o file.o",
            output = EnvPath("file.o")
        )

        val parsedCommand= CompilerCommandParser().parse(projectDir, command)

        parsedCommand.errors.shouldBeEmpty()
        parsedCommand.arguments.shouldContainInOrder(
            "gcc",
            "-isystemfoo",
            "-Ibar",
            "-I",
            "baz",
            "-c",
            "file.c",
            "-o",
            "file.o",
        )
    }

    @Test
    fun `response file missing`(@TempDir projectDir: Path) {
        val sourceDir = (projectDir / "src").createDirectories()

        val responseFile = sourceDir / "file.rsp"

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf("gcc", "@${responseFile.name}", "-c", "file.c", "-o", "file.o"),
            output = EnvPath("file.o")
        )

        val parsedCommand= CompilerCommandParser().parse(projectDir, command)

        parsedCommand.errors shouldHaveSize 1
        parsedCommand.errors[0] shouldStartWith "No such file: "

        parsedCommand.arguments.shouldContainInOrder(
            "gcc",
            "-c",
            "file.c",
            "-o",
            "file.o",
        )
    }
}
