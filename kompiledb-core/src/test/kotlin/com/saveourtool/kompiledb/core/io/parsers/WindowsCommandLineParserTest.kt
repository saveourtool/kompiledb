package com.saveourtool.kompiledb.core.io.parsers

import com.saveourtool.kompiledb.core.io.CommandLineParser
import io.kotest.matchers.collections.shouldContainExactly
import kotlin.test.Test

/**
 * @see WindowsCommandLineParser
 */
class WindowsCommandLineParserTest {
    private val parser: CommandLineParser = WindowsCommandLineParser

    @Test
    fun `command should be split on whitespace`() {
        parser("clang -c file.c").shouldContainExactly("clang", "-c", "file.c")
    }

    @Test
    fun `leading and trailing whitespace should be trimmed`() {
        parser(" clang -c file.c").shouldContainExactly("clang", "-c", "file.c")
        parser("clang -c file.c ").shouldContainExactly("clang", "-c", "file.c")
        parser(" clang -c file.c ").shouldContainExactly("clang", "-c", "file.c")
        parser(" clang").shouldContainExactly("clang")
        parser("clang ").shouldContainExactly("clang")
        parser(" clang ").shouldContainExactly("clang")
    }

    @Test
    fun `double-quoted arguments should be handled correctly`() {
        parser(""""clang" -c file.c""").shouldContainExactly("clang", "-c", "file.c")
        parser("""clang "-c" file.c""").shouldContainExactly("clang", "-c", "file.c")
        parser("""clang -c "file.c"""").shouldContainExactly("clang", "-c", "file.c")
        parser(""""clang" "-c" "file.c"""").shouldContainExactly("clang", "-c", "file.c")
        parser(""""/ path / to / clang" "-c" "f i l e.c"""").shouldContainExactly("/ path / to / clang", "-c", "f i l e.c")
    }

    @Test
    fun `zero-length quoted arguments should be preserved`() {
        parser("""clang -c """"").shouldContainExactly("clang", "-c", "")
        parser(""""" "" """"").shouldContainExactly("", "", "")
    }

    @Test
    fun `adjacent quoted fragments glued together (no whitespace)`() {
        parser("""clang -c "fi""le.c"""").shouldContainExactly("clang", "-c", "file.c")
        parser("clang -c \"\"\"file.c\"").shouldContainExactly("clang", "-c", "file.c")
        parser("clang -c \"file.c\"\"\"").shouldContainExactly("clang", "-c", "file.c")
    }

    @Test
    fun `adjacent quoted and non-quoted fragments glued together (no whitespace)`() {
        parser("""clang -c "fi"le.c""").shouldContainExactly("clang", "-c", "file.c")
        parser("""clang -c fi"le.c"""").shouldContainExactly("clang", "-c", "file.c")
        parser("""clang -c ""file.c""").shouldContainExactly("clang", "-c", "file.c")
        parser("""clang -c file.c""""").shouldContainExactly("clang", "-c", "file.c")
    }

    @Test
    fun `single quotation marks inside double-quoted arguments`() {
        parser("""clang -c "'file1.c'" "'file2.c'"""").shouldContainExactly("clang", "-c", "'file1.c'", "'file2.c'")
        parser("""clang -c "'f i l e 1.c'" "'f i l e 2.c'"""").shouldContainExactly("clang", "-c", "'f i l e 1.c'", "'f i l e 2.c'")
    }
}
