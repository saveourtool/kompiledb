//[kompiledb-core](../../index.md)/[com.saveourtool.kompiledb.core](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [CompilationCommand](-compilation-command/index.md) | [jvm]<br>data class [CompilationCommand](-compilation-command/index.md)(val directory: [EnvPath](-env-path/index.md), val file: [EnvPath](-env-path/index.md), val arguments: [ParsedArgs](../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), val command: [RawCommandLine](../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835)? = null, val output: [EnvPath](-env-path/index.md)? = null)<br>A [*JSON Compilation Database*](https://clang.llvm.org/docs/JSONCompilationDatabase.html) entry. |
| [CompilationDatabase](-compilation-database/index.md) | [jvm]<br>data class [CompilationDatabase](-compilation-database/index.md)(val commands: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](-compilation-command/index.md)&gt;, val errors: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyMap())<br>A [*JSON Compilation Database*](https://clang.llvm.org/docs/JSONCompilationDatabase.html). |
| [EnvPath](-env-path/index.md) | [jvm]<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)<br>value class [EnvPath](-env-path/index.md)(val pathString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>The path in the target build environment. |
| [JsonIo](-json-io/index.md) | [jvm]<br>interface [JsonIo](-json-io/index.md) |
