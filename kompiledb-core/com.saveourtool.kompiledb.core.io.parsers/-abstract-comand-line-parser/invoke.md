//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io.parsers](../index.md)/[AbstractComandLineParser](index.md)/[invoke](invoke.md)

# invoke

[jvm]\
operator override fun [invoke](invoke.md)(rawCommandLine: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835)): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835)

Splits [rawCommandLine](invoke.md) into individual arguments.

#### Return

the compile command `argv` as a list of strings, in a form ready to be passed to `execvp()` (i.e. un-escaped).
