---
title: parse
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.compiler](../index.html)/[CompilerCommandParser](index.html)/[parse](parse.html)



# parse



[jvm]\
abstract fun [parse](parse.html)(databasePath: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), command: [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html)): [ParsedCompilerCommand](../-parsed-compiler-command/index.html)



Parses the compilation command.



#### Return



the parsed command.



#### Parameters


jvm

| | |
|---|---|
| databasePath | the path to the compilation database JSON file (or its parent directory) on the local file system. |
| command | the compilation command to parse. |




