package com.saveourtool.kompiledb.core.io.parsers

import com.saveourtool.kompiledb.core.io.CommandLineParser
import io.kotest.matchers.collections.shouldContainInOrder
import kotlin.test.Test

/**
 * @see PosixShellCommandLineParser
 */
class PosixShellCommandLineParserTest {
    private val parser: CommandLineParser = PosixShellCommandLineParser

    @Test
    fun `command should be split on whitespace`() {
        parser("gcc -c file.c").shouldContainInOrder("gcc", "-c", "file.c")
    }

    @Test
    fun `leading and trailing whitespace should be trimmed`() {
        parser(" gcc -c file.c").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c file.c ").shouldContainInOrder("gcc", "-c", "file.c")
        parser(" gcc -c file.c ").shouldContainInOrder("gcc", "-c", "file.c")
        parser(" gcc").shouldContainInOrder("gcc")
        parser("gcc ").shouldContainInOrder("gcc")
        parser(" gcc ").shouldContainInOrder("gcc")
    }

    @Test
    fun `double-quoted arguments should be handled correctly`() {
        parser(""""gcc" -c file.c""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc "-c" file.c""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc -c "file.c"""").shouldContainInOrder("gcc", "-c", "file.c")
        parser(""""gcc" "-c" "file.c"""").shouldContainInOrder("gcc", "-c", "file.c")
        parser(""""/ path / to / gcc" "-c" "f i l e.c"""").shouldContainInOrder("/ path / to / gcc", "-c", "f i l e.c")
    }

    @Test
    fun `single-quoted arguments should be handled correctly`() {
        parser("'gcc' -c file.c").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc '-c' file.c").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c 'file.c'").shouldContainInOrder("gcc", "-c", "file.c")
        parser("'gcc' '-c' 'file.c'").shouldContainInOrder("gcc", "-c", "file.c")
        parser("'/ path / to / gcc' '-c' 'f i l e.c'").shouldContainInOrder("/ path / to / gcc", "-c", "f i l e.c")
    }

    @Test
    fun `zero-length quoted arguments should be preserved`() {
        parser("""gcc -c """"").shouldContainInOrder("gcc", "-c", "")
        parser(""""" "" """"").shouldContainInOrder("", "", "")
        parser("gcc -c ''").shouldContainInOrder("gcc", "-c", "")
        parser("'' '' ''").shouldContainInOrder("", "", "")
    }

    @Test
    fun `adjacent quoted fragments glued together (no whitespace)`() {
        parser("""gcc -c "fi""le.c"""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c \"\"\"file.c\"").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c \"file.c\"\"\"").shouldContainInOrder("gcc", "-c", "file.c")

        parser("""gcc -c "fi"'le.c'""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc -c ""'file.c'""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc -c "file.c"''""").shouldContainInOrder("gcc", "-c", "file.c")

        parser("""gcc -c 'fi'"le.c"""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc -c ''"file.c"""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc -c 'file.c'""""").shouldContainInOrder("gcc", "-c", "file.c")

        parser("gcc -c 'fi''le.c'").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c '''file.c'").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c 'file.c'''").shouldContainInOrder("gcc", "-c", "file.c")
    }

    @Test
    fun `adjacent quoted and non-quoted fragments glued together (no whitespace)`() {
        parser("""gcc -c "fi"le.c""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc -c fi"le.c"""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc -c ""file.c""").shouldContainInOrder("gcc", "-c", "file.c")
        parser("""gcc -c file.c""""").shouldContainInOrder("gcc", "-c", "file.c")

        parser("gcc -c 'fi'le.c").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c fi'le.c'").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c ''file.c").shouldContainInOrder("gcc", "-c", "file.c")
        parser("gcc -c file.c''").shouldContainInOrder("gcc", "-c", "file.c")
    }

    @Test
    fun `single quotation marks inside double-quoted arguments`() {
        parser("""gcc -c "'file1.c'" "'file2.c'"""").shouldContainInOrder("gcc", "-c", "'file1.c'", "'file2.c'")
        parser("""gcc -c "'f i l e 1.c'" "'f i l e 2.c'"""").shouldContainInOrder("gcc", "-c", "'f i l e 1.c'", "'f i l e 2.c'")
    }

    @Test
    fun `double quotation marks inside single-quoted arguments`() {
        parser("""gcc -c '"file1.c"' '"file2.c"'""").shouldContainInOrder("gcc", "-c", """"file1.c"""", """"file2.c"""")
        parser("""gcc -c '"f i l e 1.c"' '"f i l e 2.c"'""").shouldContainInOrder("gcc", "-c", """"f i l e 1.c"""", """"f i l e 2.c"""")
    }
}
