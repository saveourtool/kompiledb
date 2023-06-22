package com.saveourtool.kompiledb.core.io.parsers

object WindowsCommandLineParser : AbstractComandLineParser(
    escapeChar = '^',
    quotationMarks = charArrayOf('"'),
)
