//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.compiler](../index.md)/[CompilerCommandParser](index.md)/[parse](parse.md)

# parse

[jvm]\
abstract fun [parse](parse.md)(databasePath: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), command: [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md)): [ParsedCompilerCommand](../-parsed-compiler-command/index.md)

Parses the compilation command.

#### Return

the parsed command.

#### Parameters

jvm

| | |
|---|---|
| databasePath | the path to the compilation database JSON file (or its parent directory) on the local file system. |
| command | the compilation command to parse. |
