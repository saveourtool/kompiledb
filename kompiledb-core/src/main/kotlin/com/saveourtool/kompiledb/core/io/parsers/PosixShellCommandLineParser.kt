package com.saveourtool.kompiledb.core.io.parsers

import com.saveourtool.kompiledb.core.io.ParsedArgs
import com.saveourtool.kompiledb.core.io.RawCommandLine
import org.intellij.lang.annotations.Language

object PosixShellCommandLineParser : AbstractComandLineParser() {
    override val escapeChar = '\\'

    override val quotationMarks: CharArray = charArrayOf('\'', '"')

    override fun invoke(@Language("sh") rawCommandLine: RawCommandLine): ParsedArgs =
        rawCommandLine.requireContainsNoEscape()
            .requireContainsNoQuotation()
            .trim()
            .split(WHITESPACE)
}
