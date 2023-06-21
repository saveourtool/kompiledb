package com.saveourtool.kompiledb.core.io.parsers

import com.saveourtool.kompiledb.core.io.CommandLineParser
import com.saveourtool.kompiledb.core.io.RawCommandLine

abstract class AbstractComandLineParser : CommandLineParser {
    protected abstract val escapeChar: Char

    protected abstract val quotationMarks: CharArray

    private val RawCommandLine.containsEscape: Boolean
        get() =
            asSequence().any(escapeChar::equals)

    private val RawCommandLine.containsQuotation: Boolean
        get() =
            asSequence().any(quotationMarks::contains)

    protected fun RawCommandLine.requireContainsNoEscape(): RawCommandLine {
        require(!containsEscape) {
            "The command line contains an escape character ($escapeChar): $this"
        }

        return this
    }

    protected fun RawCommandLine.requireContainsNoQuotation(): RawCommandLine {
        require(!containsQuotation) {
            "The command line contains a quotation mark (${quotationMarks.toList()}): $this"
        }

        return this
    }

    protected companion object {
        @JvmStatic
        protected val WHITESPACE = Regex("""\s+""")
    }
}
