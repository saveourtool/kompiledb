package com.saveourtool.kompiledb.core.io.parsers

import com.saveourtool.kompiledb.core.io.Arg
import com.saveourtool.kompiledb.core.io.CommandLineParser
import com.saveourtool.kompiledb.core.io.ParsedArgs
import com.saveourtool.kompiledb.core.io.RawCommandLine

abstract class AbstractComandLineParser(
    private val escapeChar: Char,
    private val quotationMarks: CharArray,
) : CommandLineParser {

    private val RawCommandLine.containsEscape: Boolean
        get() =
            asSequence().any(escapeChar::equals)

    private val Char.isQuotationMark: Boolean
        get() =
            this in quotationMarks

    /**
     * Splits [rawCommandLine] into individual arguments.
     *
     * @return the compile command `argv` as a list of strings, in a form ready
     *   to be passed to `execvp()` (i.e. un-escaped).
     */
    final override fun invoke(rawCommandLine: RawCommandLine): ParsedArgs =
        rawCommandLine
            .requireContainsNoEscape()
            .requireContainsNoNewline()
            .toParsedArgs()
            .toList()

    /**
     * See [Parsing C++ Command-Line Arguments](https://msdn.microsoft.com/en-us/library/17w5ykft(v=vs.85).aspx).
     */
    private fun RawCommandLine.toParsedArgs(): Sequence<Arg> =
        sequence {
            val arg = StringBuilder()

            /*
             * Whether the whole argument or any of its fragments is quoted.
             */
            var isArgQuoted = false

            /*
             * Whether we're currently between `"` and `"`.
             *
             * Two boolean flags instead of a single one are necessary to handle
             * complex cases like:
             *
             * - `$ echo 'foo''bar'` -> `foobar`.
             * - `$ echo 'foo'bar` -> `foobar`.
             */
            var openingQuote: Char? = null
            val isCurrentArgFragmentQuoted: () -> Boolean = {
                openingQuote != null
            }

            forEach { c ->
                @Suppress("KotlinConstantConditions")
                when {
                    c.isQuotationMark -> when {
                        isCurrentArgFragmentQuoted() -> when (c) {
                            /*
                             * Closing quote.
                             */
                            openingQuote -> openingQuote = null

                            /*
                             * Quoted quotation mark is a part of the argument.
                             */
                            else -> arg += c
                        }

                        /*
                         * Opening quote.
                         */
                        else -> {
                            openingQuote = c
                            isArgQuoted = true
                        }
                    }

                    c.isShellWhitespace -> when {
                        isCurrentArgFragmentQuoted() -> arg += c

                        /*
                         * The usual argument separators.
                         */
                        else -> {
                            yieldAndResetState(arg, isArgQuoted)
                            isArgQuoted = false
                        }
                    }

                    else -> arg += c
                }
            }

            yieldAndResetState(arg, isArgQuoted)
        }

    private fun RawCommandLine.requireContainsNoEscape(): RawCommandLine {
        require(!containsEscape) {
            "The command line contains an escape character ($escapeChar): $this"
        }

        return this
    }

    protected companion object {
        private const val SPACE = ' '

        private const val TAB = '\t'

        private const val FORM_FEED = '\u000C'

        /**
         * Whitespace which is not a newline.
         */
        private val SHELL_WHITESPACE_CHARS = charArrayOf(SPACE, TAB, FORM_FEED)

        private val NEWLINE_CHARS = charArrayOf('\r', '\n')

        private val Char.isShellWhitespace: Boolean
            get() =
                this in SHELL_WHITESPACE_CHARS

        private val Char.isNewline: Boolean
            get() =
                this in NEWLINE_CHARS

        private val RawCommandLine.containsNewline: Boolean
            get() =
                asSequence().any { char ->
                    char.isNewline
                }

        /**
         * @param isQuoted whether the whole [arg] or any of its fragments is
         *   double-quoted.
         */
        private suspend fun SequenceScope<Arg>.yieldAndResetState(
            arg: StringBuilder,
            isQuoted: Boolean,
        ) {
            var arg0: CharSequence = arg

            if (!isQuoted) {
                arg0 = arg0.trimEnd()
            }

            if (arg0.isNotEmpty() || isQuoted) {
                yield(arg0.toString())
            }

            arg.clear()
        }

        private fun RawCommandLine.requireContainsNoNewline(): RawCommandLine {
            require(!containsNewline) {
                "The command line contains a newline character: $this"
            }

            return this
        }

        private operator fun Appendable.plusAssign(c: Char) {
            append(c)
        }
    }
}
