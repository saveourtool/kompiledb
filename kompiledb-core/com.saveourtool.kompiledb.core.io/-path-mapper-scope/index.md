---
title: PathMapperScope
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io](../index.html)/[PathMapperScope](index.html)



# PathMapperScope



[jvm]\
interface [PathMapperScope](index.html)

A set of extensions to [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html) which are not dependent on the implementation-specific details of a [PathMapper](../-path-mapper/index.html).



## Types


| Name | Summary |
|---|---|
| [Companion](-companion/index.html) | [jvm]<br>object [Companion](-companion/index.html) |


## Properties


| Name | Summary |
|---|---|
| [name](name.html) | [jvm]<br>open val [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[name](name.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [pathMapper](path-mapper.html) | [jvm]<br>abstract val [pathMapper](path-mapper.html): [PathMapper](../-path-mapper/index.html)<br>The path mapper which performs path translation. |


## Functions


| Name | Summary |
|---|---|
| [div](div.html) | [jvm]<br>@Contract(pure = true)<br>open operator fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[div](div.html)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)<br>@Contract(pure = true)<br>open operator fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[div](div.html)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;<br>Resolves the given [other](div.html) path against this path. |
| [resolve](resolve.html) | [jvm]<br>@Contract(pure = true)<br>open infix fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[resolve](resolve.html)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)<br>@Contract(pure = true)<br>open infix fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[resolve](resolve.html)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;<br>Resolve the given [other](resolve.html) path against this path. |

