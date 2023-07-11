//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io.parsers](../index.md)/[WindowsCommandLineParser](index.md)

# WindowsCommandLineParser

[jvm]\
object [WindowsCommandLineParser](index.md) : [AbstractComandLineParser](../-abstract-comand-line-parser/index.md)

## Properties

| Name | Summary |
|---|---|
| [parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.md) | [jvm]<br>open val [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md).[parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.md): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835)<br>Splits the receiver into individual arguments. |

## Functions

| Name | Summary |
|---|---|
| [invoke](../-abstract-comand-line-parser/invoke.md) | [jvm]<br>operator override fun [invoke](../-abstract-comand-line-parser/invoke.md)(rawCommandLine: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835)): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835)<br>Splits [rawCommandLine](../-abstract-comand-line-parser/invoke.md) into individual arguments. |
