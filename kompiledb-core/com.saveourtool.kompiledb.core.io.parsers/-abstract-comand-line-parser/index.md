//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io.parsers](../index.md)/[AbstractComandLineParser](index.md)

# AbstractComandLineParser

abstract class [AbstractComandLineParser](index.md)(escapeChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html), quotationMarks: [CharArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-array/index.html)) : [CommandLineParser](../../com.saveourtool.kompiledb.core.io/-command-line-parser/index.md)

#### Inheritors

| |
|---|
| [PosixShellCommandLineParser](../-posix-shell-command-line-parser/index.md) |
| [WindowsCommandLineParser](../-windows-command-line-parser/index.md) |

## Constructors

| | |
|---|---|
| [AbstractComandLineParser](-abstract-comand-line-parser.md) | [jvm]<br>constructor(escapeChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html), quotationMarks: [CharArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-array/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.md) | [jvm]<br>open val [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md).[parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.md): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835)<br>Splits the receiver into individual arguments. |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [jvm]<br>operator override fun [invoke](invoke.md)(rawCommandLine: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835)): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835)<br>Splits [rawCommandLine](invoke.md) into individual arguments. |
