//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[CompilationDatabase](index.md)

# CompilationDatabase

data class [CompilationDatabase](index.md)(val commands: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](../-compilation-command/index.md)&gt;, val errors: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyMap())

A [*JSON Compilation Database*](https://clang.llvm.org/docs/JSONCompilationDatabase.html).

#### Parameters

jvm

| | |
|---|---|
| commands | the list of compilation commands. |
| errors | the errors encountered when reading an external file (not a part of the standard, not serialized to JSON). |

## Constructors

| | |
|---|---|
| [CompilationDatabase](-compilation-database.md) | [jvm]<br>constructor(vararg commands: [CompilationCommand](../-compilation-command/index.md))<br>constructor(commands: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](../-compilation-command/index.md)&gt;, errors: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyMap()) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [commands](commands.md) | [jvm]<br>val [commands](commands.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](../-compilation-command/index.md)&gt; |
| [errors](errors.md) | [jvm]<br>val [errors](errors.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
