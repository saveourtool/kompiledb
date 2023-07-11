//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[CompilationDatabase](index.md)/[CompilationDatabase](-compilation-database.md)

# CompilationDatabase

[jvm]\
constructor(vararg commands: [CompilationCommand](../-compilation-command/index.md))

#### Parameters

jvm

| | |
|---|---|
| commands | the list of compilation commands. |

[jvm]\
constructor(commands: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](../-compilation-command/index.md)&gt;, errors: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyMap())

#### Parameters

jvm

| | |
|---|---|
| commands | the list of compilation commands. |
| errors | the errors encountered when reading an external file (not a part of the standard, not serialized to JSON). |
