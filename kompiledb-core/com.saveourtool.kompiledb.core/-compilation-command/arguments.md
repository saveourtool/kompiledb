---
title: arguments
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core](../index.html)/[CompilationCommand](index.html)/[arguments](arguments.html)



# arguments



[jvm]\
val [arguments](arguments.html): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792)



The compile command `argv` as list of strings. This should run the compilation step for the translation unit [file](file.html). `arguments[0]` should be the executable name, such as `clang++`. Arguments should not be escaped, but ready to pass to `execvp()`.




