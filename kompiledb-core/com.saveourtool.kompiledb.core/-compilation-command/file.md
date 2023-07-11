//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[CompilationCommand](index.md)/[file](file.md)

# file

[jvm]\
val [file](file.md): [EnvPath](../-env-path/index.md)

The main translation unit source processed by this compilation step. This is used by tools as the key into the compilation database. There can be multiple command objects for the same file, for example if the same source file is compiled with different configurations.
