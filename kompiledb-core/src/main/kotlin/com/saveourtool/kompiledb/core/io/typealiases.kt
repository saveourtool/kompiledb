package com.saveourtool.kompiledb.core.io

/**
 * A raw command line (possibly containing quotation marks and escape characters).
 *
 * @see Arg
 * @see ParsedArgs
 */
typealias RawCommandLine = String

/**
 * An argument of a parsed command line.
 *
 * @see ParsedArgs
 * @see RawCommandLine
 */
typealias Arg = String

/**
 * A parsed command line.
 *
 * @see Arg
 * @see RawCommandLine
 */
typealias ParsedArgs = List<Arg>
