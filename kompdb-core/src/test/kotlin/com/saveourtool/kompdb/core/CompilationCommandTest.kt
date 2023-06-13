package com.saveourtool.kompdb.core

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlin.test.Test
import com.saveourtool.kompdb.core.EnvPath as Path

/**
 * @see CompilationCommand
 */
class CompilationCommandTest {
    @Test
    fun `should fail if file is empty`() {
        val iae = shouldThrow<IllegalArgumentException> {
            CompilationCommand(Path(""), Path(""), "gcc -c file.c -o file.o")
        }

        iae.message.shouldNotBeNull() shouldBeEqual "`file` shouldn't be blank"
    }

    @Test
    fun `should fail if arguments are empty`() {
        val iae = shouldThrow<IllegalArgumentException> {
            CompilationCommand(Path(""), Path(""), arguments = emptyList())
        }

        iae.message.shouldNotBeNull() shouldBeEqual "Either `arguments` or `command` is required"
    }

    @Test
    fun `should fail if command is empty`() {
        val iae = shouldThrow<IllegalArgumentException> {
            CompilationCommand(Path(""), Path(""), command = "")
        }

        iae.message.shouldNotBeNull() shouldBeEqual "Either `arguments` or `command` is required"
    }
}
