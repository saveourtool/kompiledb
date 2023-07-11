//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.compiler](../index.md)/[CompilerCommandParser](index.md)

# CompilerCommandParser

[jvm]\
fun interface [CompilerCommandParser](index.md)

Parses a [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md) in a compiler-specific way.

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | [jvm]<br>abstract fun [parse](parse.md)(databasePath: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), command: [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md)): [ParsedCompilerCommand](../-parsed-compiler-command/index.md)<br>Parses the compilation command. |
