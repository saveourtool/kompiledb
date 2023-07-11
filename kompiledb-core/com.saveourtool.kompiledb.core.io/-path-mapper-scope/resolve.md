//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io](../index.md)/[PathMapperScope](index.md)/[resolve](resolve.md)

# resolve

[jvm]\

@Contract(pure = true)

open infix fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[resolve](resolve.md)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)

@Contract(pure = true)

open infix fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[resolve](resolve.md)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;

Resolve the given [other](resolve.md) path against this path.

If the [other](resolve.md) parameter is an absolute path then this method trivially returns [other](resolve.md). If [other](resolve.md) is an [*empty path*](../../com.saveourtool.kompiledb.core/-env-path/is-empty.md) then this method trivially returns this path. Otherwise this method considers this path to be a directory and resolves the given path against this path.

#### Return

the resulting path.

#### Parameters

jvm

| | |
|---|---|
| other | the path to resolve against this path. |
