package com.saveourtool.kompiledb.core.compiler

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import com.saveourtool.kompiledb.core.EnvPath.Companion.EMPTY
import com.saveourtool.kompiledb.core.lang.C
import com.saveourtool.kompiledb.core.lang.Cxx
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.paths.shouldBeAbsolute
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.io.TempDir
import java.io.File.separatorChar
import java.nio.file.Path
import kotlin.io.path.Path
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
            arguments = listOf("clang", "@${responseFile.name}", "-c", "file.c", "-o", "file.o"),
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.parseErrors.shouldBeEmpty()
        parsedCommand.arguments.shouldContainExactly(
            "clang",
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
            command = "clang @${responseFile.name} -c file.c -o file.o",
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.parseErrors.shouldBeEmpty()
        parsedCommand.arguments.shouldContainExactly(
            "clang",
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
            arguments = listOf("clang", "@${responseFile.name}", "-c", "file.c", "-o", "file.o"),
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.parseErrors shouldHaveSize 1
        parsedCommand.parseErrors[0] shouldStartWith "No such file: "

        parsedCommand.arguments.shouldContainExactly(
            "clang",
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
                "clang",
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
    fun `include directories (quoted)`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            command = """clang -I"subdir1" -I "subdir2" "-Isubdir3" -c file.c -o file.o""",
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        val flattenedIncludePaths = parsedCommand.includePaths()
            .asSequence()
            .flatMap { includePaths ->
                includePaths.value.asSequence()
            }
            .toList()

        flattenedIncludePaths
            .asSequence()
            .map { includePath ->
                includePath.relativeTo(sourceDir)
            }
            .map(Path::pathString)
            .toList()
            .shouldContainExactlyInAnyOrder(
                "subdir1",
                "subdir2",
                "subdir3",
            )
    }

    @Test
    fun `empty quoted include directory should be preserved`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            command = """clang -I "" -c file.c -o file.o""",
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        val flattenedIncludePaths = parsedCommand.includePaths()
            .asSequence()
            .flatMap { includePaths ->
                includePaths.value.asSequence()
            }
            .toList()

        flattenedIncludePaths
            .asSequence()
            .map { includePath ->
                includePath.relativeTo(sourceDir)
            }
            .map(Path::pathString)
            .toList()
            .shouldContainExactly("")
    }

    @Test
    fun `include files`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf(
                "clang",
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

    @Test
    fun `defined macros`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf(
                "clang",
                "-DMACRO1",
                "-DMACRO2=2",
                "-D", "MACRO3=\"Three\"",
                "-c",
                "file.c",
                "-o",
                "file.o",
            ),
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.definedMacros.shouldContainExactly(
            mapOf(
                "MACRO1" to "1",
                "MACRO2" to "2",
                "MACRO3" to "\"Three\"",
            )
        )
    }

    @Test
    fun `undefined macros`(@TempDir projectRoot: Path) {
        val sourceDir = (projectRoot / "src").createDirectories()

        val command = CompilationCommand(
            directory = EnvPath(pathString = "./${sourceDir.name}/"),
            file = EnvPath("file.c"),
            arguments = listOf(
                "clang",
                "-UMACRO1",
                "-U", "MACRO2",
                "-c",
                "file.c",
                "-o",
                "file.o",
            ),
            output = EnvPath("file.o")
        )

        val parsedCommand = CompilerCommandParser().parse(projectRoot, command)

        parsedCommand.undefinedMacros.shouldContainExactlyInAnyOrder(
            "MACRO1",
            "MACRO2",
        )
    }

    @Test
    fun `dash-x switch should get parsed (C)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -xc -c file",
            "clang -x c -c file",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language shouldBe C }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `dash-x switch should get parsed, the last occurrence taking precedence (C)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -xc++ -xc -c file",
            "clang -xc++ -x c -c file",
            "clang -x c++ -xc -c file",
            "clang -x c++ -x c -c file",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language shouldBe C }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `dash-x switch should get parsed (C++)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -xc++ -c file",
            "clang -x c++ -c file",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language shouldBe Cxx }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `dash-x switch should get parsed, the last occurrence taking precedence (C++)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -xc -xc++ -c file",
            "clang -xc -x c++ -c file",
            "clang -x c -xc++ -c file",
            "clang -x c -x c++ -c file",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language shouldBe Cxx }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `dash-x switch should get parsed (custom)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -xobjective-c -c file",
            "clang -x objective-c -c file",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language.name shouldBeEqual "objective-c" }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `language should get inferred from the extension if unspecified (C)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -c file.c",
            "clang++ -c file.c",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file.c"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language shouldBe C }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `language should get inferred from the extension if unspecified (C++)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -c file.cc",
            "clang++ -c file.cc",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file.cc"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language shouldBe Cxx }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `language should get inferred from the extension if unspecified (custom)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -c file.f77",
            "clang++ -c file.f77",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file.f77"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language.name shouldBeEqual "f77" }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `dash-x none should be ignored (C)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -x none -c file.c",
            "clang++ -xnone -c file.c",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file.c"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language shouldBe C }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `dash-x none should be ignored (C++)`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -x none -c file.cc",
            "clang++ -xnone -c file.cc",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file.cc"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language shouldBe Cxx }.lazyUnit
                }
                .toList()
        )
    }

    @Test
    fun `language should be unknown if no extension`() {
        @Language("sh")
        val commands = sequenceOf(
            "clang -c file",
            "clang++ -c file",
        )

        assertAll(
            commands
                .map { command ->
                    CompilationCommand(
                        directory = EMPTY,
                        file = EnvPath("file"),
                        command = command,
                    )
                }
                .map { command ->
                    CompilerCommandParser().parse(Path(""), command)
                }
                .map { command ->
                    { command.language.name shouldBeEqual "unknown" }.lazyUnit
                }
                .toList()
        )
    }

    private companion object {
        private val <T> (() -> T).lazyUnit: () -> Unit
            get() = {
                this()
                Unit
            }
    }
}
