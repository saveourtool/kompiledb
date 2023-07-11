---
title: com.saveourtool.kompiledb.core
---
//[kompiledb-core](../../index.html)/[com.saveourtool.kompiledb.core](index.html)



# Package-level declarations



## Types


| Name | Summary |
|---|---|
| [CompilationCommand](-compilation-command/index.html) | [jvm]<br>data class [CompilationCommand](-compilation-command/index.html)(val directory: [EnvPath](-env-path/index.html), val file: [EnvPath](-env-path/index.html), val arguments: [ParsedArgs](../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), val command: [RawCommandLine](../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792)? = null, val output: [EnvPath](-env-path/index.html)? = null)<br>A [*JSON Compilation Database*](https://clang.llvm.org/docs/JSONCompilationDatabase.html) entry. |
| [CompilationDatabase](-compilation-database/index.html) | [jvm]<br>data class [CompilationDatabase](-compilation-database/index.html)(val commands: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](-compilation-command/index.html)&gt;, val errors: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyMap())<br>A [*JSON Compilation Database*](https://clang.llvm.org/docs/JSONCompilationDatabase.html). |
| [EnvPath](-env-path/index.html) | [jvm]<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)<br>value class [EnvPath](-env-path/index.html)(val pathString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>The path in the target build environment. |
| [JsonIo](-json-io/index.html) | [jvm]<br>interface [JsonIo](-json-io/index.html) |

