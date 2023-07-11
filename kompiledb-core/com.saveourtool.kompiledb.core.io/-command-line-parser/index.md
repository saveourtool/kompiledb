---
title: CommandLineParser
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io](../index.html)/[CommandLineParser](index.html)



# CommandLineParser

interface [CommandLineParser](index.html) : [Function1](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-function1/index.html)&lt;[RawCommandLine](../index.html#1075615255%2FClasslikes%2F-204370792), [ParsedArgs](../index.html#1743527040%2FClasslikes%2F-204370792)&gt; 

Command-line parser.



It can't handle multiple commands separated with `&`, `&&`, `|`, `||` etc., so any multi-line batch file code should be previously split into individual commands.



#### See also


| |
|---|
| [CommandLineParser.parsedArguments](parsed-arguments.html) |


#### Inheritors


| |
|---|
| [AbstractComandLineParser](../../com.saveourtool.kompiledb.core.io.parsers/-abstract-comand-line-parser/index.html) |


## Types


| Name | Summary |
|---|---|
| [Companion](-companion/index.html) | [jvm]<br>object [Companion](-companion/index.html) |


## Properties


| Name | Summary |
|---|---|
| [parsedArguments](parsed-arguments.html) | [jvm]<br>open val [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html).[parsedArguments](parsed-arguments.html): [ParsedArgs](../index.html#1743527040%2FClasslikes%2F-204370792)<br>Splits the receiver into individual arguments. |


## Functions


| Name | Summary |
|---|---|
| [invoke](index.html#98273987%2FFunctions%2F-204370792) | [jvm]<br>abstract operator fun [invoke](index.html#98273987%2FFunctions%2F-204370792)(p1: [RawCommandLine](../index.html#1075615255%2FClasslikes%2F-204370792)): [ParsedArgs](../index.html#1743527040%2FClasslikes%2F-204370792) |

