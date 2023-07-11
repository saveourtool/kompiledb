---
title: parsedArguments
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io](../index.html)/[CommandLineParser](index.html)/[parsedArguments](parsed-arguments.html)



# parsedArguments



[jvm]\
open val [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html).[parsedArguments](parsed-arguments.html): [ParsedArgs](../index.html#1743527040%2FClasslikes%2F-204370792)



Splits the receiver into individual arguments.



#### Return



the compile command `argv` as a list of strings, in a form ready to be passed to `execvp()` (i.e. un-escaped).



#### See also


| |
|---|
| [CompilationCommand.arguments](../../com.saveourtool.kompiledb.core/-compilation-command/arguments.html) |



