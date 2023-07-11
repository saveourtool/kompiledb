---
title: CompilerCommandParser
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.compiler](../index.html)/[CompilerCommandParser](index.html)



# CompilerCommandParser



[jvm]\
fun interface [CompilerCommandParser](index.html)

Parses a [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html) in a compiler-specific way.



## Types


| Name | Summary |
|---|---|
| [Companion](-companion/index.html) | [jvm]<br>object [Companion](-companion/index.html) |


## Functions


| Name | Summary |
|---|---|
| [parse](parse.html) | [jvm]<br>abstract fun [parse](parse.html)(databasePath: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), command: [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html)): [ParsedCompilerCommand](../-parsed-compiler-command/index.html)<br>Parses the compilation command. |

