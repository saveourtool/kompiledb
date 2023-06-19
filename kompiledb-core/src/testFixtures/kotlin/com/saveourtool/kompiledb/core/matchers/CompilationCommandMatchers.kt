@file:JvmName("CompilationCommandMatchers")

package com.saveourtool.kompiledb.core.matchers

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.reflection.compose
import io.kotest.matchers.shouldBe
import org.intellij.lang.annotations.Language

fun CompilationCommand.shouldBeCommand(
    directory: String = "",
    file: String = "",
    arguments: List<String> = emptyList(),
    @Language("sh")
    command: String? = null,
    output: String? = null,
): CompilationCommand {
    this shouldBe commandMatcher(
        directory.let(::EnvPath),
        file.let(::EnvPath),
        arguments,
        command,
        output?.let(::EnvPath),
    )
    return this
}

fun directoryMatcher(expected: EnvPath): Matcher<EnvPath> =
    pathMatcher(expected, "directory")

fun fileMatcher(expected: EnvPath): Matcher<EnvPath> =
    pathMatcher(expected, "file")

fun argumentsMatcher(expected: List<String>): Matcher<List<String>> =
    matcher(expected, "arguments", ::formatStrings)

fun commandMatcher(expected: String?): Matcher<String?> =
    matcher(expected, "command", ::formatString)

fun outputMatcher(expected: EnvPath?): Matcher<EnvPath?> =
    pathMatcher(expected, "output")

private fun pathMatcher(expected: EnvPath?, messagePrefix: String): Matcher<EnvPath?> =
    matcher(expected, messagePrefix, ::formatPath)

private fun commandMatcher(
    directory: EnvPath,
    file: EnvPath,
    arguments: List<String>,
    @Language("sh")
    command: String?,
    output: EnvPath?,
): Matcher<CompilationCommand> =
    Matcher.compose(
        directoryMatcher(directory) to CompilationCommand::directory,
        fileMatcher(file) to CompilationCommand::file,
        argumentsMatcher(arguments) to CompilationCommand::arguments,
        commandMatcher(command) to CompilationCommand::command,
        outputMatcher(output) to CompilationCommand::output,
    )

private fun <T> matcher(expected: T, messagePrefix: String, format: (T) -> String): Matcher<T> =
    Matcher { actual ->
        MatcherResult(
            actual == expected,
            { "$messagePrefix expected: <$expected> but was: <$actual>" },
            { "$messagePrefix expected: <not ${format(expected)}> but was: <$actual>" },
        )
    }

private fun formatStrings(s: List<String>): String =
    when {
        s.isEmpty() -> "empty"
        else -> s.toString()
    }

private fun formatString(s: String?): String =
    when {
        s == null -> "null"
        s.isEmpty() -> "empty"
        else -> s
    }

private fun formatPath(path: EnvPath?): String =
    formatString(path?.pathString)
