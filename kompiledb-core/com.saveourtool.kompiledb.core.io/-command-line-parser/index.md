//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io](../index.md)/[CommandLineParser](index.md)

# CommandLineParser

interface [CommandLineParser](index.md) : [Function1](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-function1/index.html)&lt;[RawCommandLine](../index.md#1075615255%2FClasslikes%2F-937334835), [ParsedArgs](../index.md#1743527040%2FClasslikes%2F-937334835)&gt; 

Command-line parser.

It can't handle multiple commands separated with `&`, `&&`, `|`, `||` etc., so any multi-line batch file code should be previously split into individual commands.

#### See also

| |
|---|
| [CommandLineParser.parsedArguments](parsed-arguments.md) |

#### Inheritors

| |
|---|
| [AbstractComandLineParser](../../com.saveourtool.kompiledb.core.io.parsers/-abstract-comand-line-parser/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [parsedArguments](parsed-arguments.md) | [jvm]<br>open val [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md).[parsedArguments](parsed-arguments.md): [ParsedArgs](../index.md#1743527040%2FClasslikes%2F-937334835)<br>Splits the receiver into individual arguments. |

## Functions

| Name | Summary |
|---|---|
| [invoke](index.md#98273987%2FFunctions%2F-937334835) | [jvm]<br>abstract operator fun [invoke](index.md#98273987%2FFunctions%2F-937334835)(p1: [RawCommandLine](../index.md#1075615255%2FClasslikes%2F-937334835)): [ParsedArgs](../index.md#1743527040%2FClasslikes%2F-937334835) |
