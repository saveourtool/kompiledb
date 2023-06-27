package com.saveourtool.kompiledb.core.compiler

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.paths.shouldBeAbsolute
import io.kotest.matchers.string.shouldStartWith
import org.junit.jupiter.api.io.TempDir
import java.io.File.separatorChar
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.div
import kotlin.io.path.name
import kotlin.io.path.pathString
import kotlin.io.path.relativeTo
import kotlin.io.path.writeText
import kotlin.test.Test

/**
 * @see CompilerCommandParser
 */
class CompilerCommandParserTest {
    @Test
    fun `response file should get expanded (arguments)`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val responseFile = (sourceDir / "file.rsp").apply {
            writeText("-isystemfoo -Ibar -I baz")
        }

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf("gcc", "@${responseFile.name}", "-c", "file.c", "-o", "file.o"),
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.parseErrors.shouldBeEmpty()
        parsedCommand.arguments.shouldContainExactly(
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
    fun `response file should get expanded (command)`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val responseFile = (sourceDir / "file.rsp").apply {
            writeText("-isystemfoo -Ibar -I baz")
        }

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            command = "gcc @${responseFile.name} -c file.c -o file.o",
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.parseErrors.shouldBeEmpty()
        parsedCommand.arguments.shouldContainExactly(
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
    fun `response file missing`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val responseFile = sourceDir / "file.rsp"

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf("gcc", "@${responseFile.name}", "-c", "file.c", "-o", "file.o"),
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.parseErrors shouldHaveSize 1
        parsedCommand.parseErrors[0] shouldStartWith "No such file: "

        parsedCommand.arguments.shouldContainExactly(
            "gcc",
            "-c",
            "file.c",
            "-o",
            "file.o",
        )
    }

    @Test
    fun `include directories`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf(
                "gcc",
                "-I.",
                "-iquote", "..",
                "-isystemsubdir1",
                "-idirafter", "subdir2",
                "-I-",
                "-c",
                "file.c",
                "-o",
                "file.o",
            ),
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.parseErrors.shouldBeEmpty()

        parsedCommand.includePaths shouldHaveSize 4

        val flattenedIncludePaths = parsedCommand.includePaths()
            .asSequence()
            .flatMap { includePaths ->
                includePaths.value.asSequence()
            }
            .toList()

        flattenedIncludePaths shouldHaveSize 4

        flattenedIncludePaths
            .asSequence()
            .onEach { includePath ->
                includePath.shouldBeAbsolute()
                includePath.normalize() shouldBeEqual includePath
            }
            .map { includePath ->
                includePath.relativeTo(projectRoot)
            }
            .map(Path::pathString)
            .toList()
            .shouldContainExactlyInAnyOrder(
                "src",
                "",
                "src${separatorChar}subdir1",
                "src${separatorChar}subdir2",
            )
    }

    @Test
    fun `include files`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf(
                "gcc",
                "-includefile1.h",
                "-imacrosfile2.h",
                "-include", "./file3.h",
                "-imacros", "../src/./file4.h",
                "-c",
                "file.c",
                "-o",
                "file.o",
            ),
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.parseErrors.shouldBeEmpty()

        parsedCommand.includePaths shouldHaveSize 2
        parsedCommand.includePaths.keys.shouldContainExactlyInAnyOrder(
            "-include",
            "-imacros",
        )

        val flattenedIncludePaths = parsedCommand.includePaths()
            .asSequence()
            .flatMap { includePaths ->
                includePaths.value.asSequence()
            }
            .toList()

        flattenedIncludePaths shouldHaveSize 4

        flattenedIncludePaths
            .asSequence()
            .onEach { includePath ->
                includePath.shouldBeAbsolute()
                includePath.normalize() shouldBeEqual includePath
            }
            .map { includePath ->
                includePath.relativeTo(sourceDir)
            }
            .map(Path::pathString)
            .toList()
            .shouldContainExactlyInAnyOrder(
                "file1.h",
                "file2.h",
                "file3.h",
                "file4.h",
            )
    }
}
