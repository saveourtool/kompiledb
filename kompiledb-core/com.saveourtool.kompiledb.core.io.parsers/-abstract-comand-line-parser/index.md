---
title: AbstractComandLineParser
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io.parsers](../index.html)/[AbstractComandLineParser](index.html)



# AbstractComandLineParser

abstract class [AbstractComandLineParser](index.html)(escapeChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html), quotationMarks: [CharArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-array/index.html)) : [CommandLineParser](../../com.saveourtool.kompiledb.core.io/-command-line-parser/index.html)

#### Inheritors


| |
|---|
| [PosixShellCommandLineParser](../-posix-shell-command-line-parser/index.html) |
| [WindowsCommandLineParser](../-windows-command-line-parser/index.html) |


## Constructors


| | |
|---|---|
| [AbstractComandLineParser](-abstract-comand-line-parser.html) | [jvm]<br>constructor(escapeChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html), quotationMarks: [CharArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-array/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.html) | [jvm]<br>open val [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html).[parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.html): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792)<br>Splits the receiver into individual arguments. |


## Functions


| Name | Summary |
|---|---|
| [invoke](invoke.html) | [jvm]<br>operator override fun [invoke](invoke.html)(rawCommandLine: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792)): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792)<br>Splits [rawCommandLine](invoke.html) into individual arguments. |

