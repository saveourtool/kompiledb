//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[CompilationCommand](index.md)/[arguments](arguments.md)

# arguments

[jvm]\
val [arguments](arguments.md): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835)

The compile command `argv` as list of strings. This should run the compilation step for the translation unit [file](file.md). `arguments[0]` should be the executable name, such as `clang++`. Arguments should not be escaped, but ready to pass to `execvp()`.
