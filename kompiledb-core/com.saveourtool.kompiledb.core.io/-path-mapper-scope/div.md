//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io](../index.md)/[PathMapperScope](index.md)/[div](div.md)

# div

[jvm]\

@Contract(pure = true)

open operator fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[div](div.md)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)

Resolves the given [other](div.md) path against this path.

This operator is a shortcut for the [EnvPath.resolve](resolve.md) function.

#### Return

the resulting path.

#### Parameters

jvm

| | |
|---|---|
| other | the path to resolve against this path. |

#### See also

| |
|---|
| [PathMapperScope.resolve](resolve.md) |

[jvm]\

@Contract(pure = true)

open operator fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[div](div.md)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;

Resolves the given [other](div.md) path against this path.

#### Return

the resulting path.

#### Parameters

jvm

| | |
|---|---|
| other | the path to resolve against this path. |
