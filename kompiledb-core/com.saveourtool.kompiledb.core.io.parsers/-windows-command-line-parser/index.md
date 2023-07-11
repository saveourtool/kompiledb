---
title: WindowsCommandLineParser
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io.parsers](../index.html)/[WindowsCommandLineParser](index.html)



# WindowsCommandLineParser



[jvm]\
object [WindowsCommandLineParser](index.html) : [AbstractComandLineParser](../-abstract-comand-line-parser/index.html)



## Properties


| Name | Summary |
|---|---|
| [parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.html) | [jvm]<br>open val [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html).[parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.html): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792)<br>Splits the receiver into individual arguments. |


## Functions


| Name | Summary |
|---|---|
| [invoke](../-abstract-comand-line-parser/invoke.html) | [jvm]<br>operator override fun [invoke](../-abstract-comand-line-parser/invoke.html)(rawCommandLine: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792)): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792)<br>Splits [rawCommandLine](../-abstract-comand-line-parser/invoke.html) into individual arguments. |

