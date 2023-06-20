package com.saveourtool.kompiledb.core.io.parsers

import com.saveourtool.kompiledb.core.io.ParsedArgs
import com.saveourtool.kompiledb.core.io.RawCommandLine

object WindowsCommandLineParser : AbstractComandLineParser() {
    override val escapeChar = '^'

    override val quotationMarks: CharArray = charArrayOf('"')

    override fun invoke(rawCommandLine: RawCommandLine): ParsedArgs =
        rawCommandLine.requireContainsNoEscape()
            .requireContainsNoQuotation()
            .trim()
            .split(WHITESPACE)
}
