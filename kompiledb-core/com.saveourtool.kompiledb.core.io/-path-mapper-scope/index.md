//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io](../index.md)/[PathMapperScope](index.md)

# PathMapperScope

[jvm]\
interface [PathMapperScope](index.md)

A set of extensions to [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md) which are not dependent on the implementation-specific details of a [PathMapper](../-path-mapper/index.md).

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | [jvm]<br>open val [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [pathMapper](path-mapper.md) | [jvm]<br>abstract val [pathMapper](path-mapper.md): [PathMapper](../-path-mapper/index.md)<br>The path mapper which performs path translation. |

## Functions

| Name | Summary |
|---|---|
| [div](div.md) | [jvm]<br>@Contract(pure = true)<br>open operator fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[div](div.md)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)<br>@Contract(pure = true)<br>open operator fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[div](div.md)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;<br>Resolves the given [other](div.md) path against this path. |
| [resolve](resolve.md) | [jvm]<br>@Contract(pure = true)<br>open infix fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[resolve](resolve.md)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)<br>@Contract(pure = true)<br>open infix fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[resolve](resolve.md)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;<br>Resolve the given [other](resolve.md) path against this path. |
