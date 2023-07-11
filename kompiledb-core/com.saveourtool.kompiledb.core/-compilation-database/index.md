---
title: CompilationDatabase
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core](../index.html)/[CompilationDatabase](index.html)



# CompilationDatabase

data class [CompilationDatabase](index.html)(val commands: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](../-compilation-command/index.html)&gt;, val errors: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyMap())

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
| [CompilationDatabase](-compilation-database.html) | [jvm]<br>constructor(vararg commands: [CompilationCommand](../-compilation-command/index.html))<br>constructor(commands: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](../-compilation-command/index.html)&gt;, errors: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyMap()) |


## Types


| Name | Summary |
|---|---|
| [Companion](-companion/index.html) | [jvm]<br>object [Companion](-companion/index.html) |


## Properties


| Name | Summary |
|---|---|
| [commands](commands.html) | [jvm]<br>val [commands](commands.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[CompilationCommand](../-compilation-command/index.html)&gt; |
| [errors](errors.html) | [jvm]<br>val [errors](errors.html): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |

