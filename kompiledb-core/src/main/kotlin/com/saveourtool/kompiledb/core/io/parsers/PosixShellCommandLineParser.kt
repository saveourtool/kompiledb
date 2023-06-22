package com.saveourtool.kompiledb.core.io.parsers

object PosixShellCommandLineParser : AbstractComandLineParser(
    escapeChar = '\\',
    quotationMarks = charArrayOf('\'', '"'),
)
