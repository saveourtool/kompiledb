//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[EnvPath](index.md)

# EnvPath

@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)

value class [EnvPath](index.md)(val pathString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

The path in the target build environment.

Use [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.md) to convert this path to a local [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).

#### Parameters

jvm

| | |
|---|---|
| pathString | the string representation of this path. |

#### See also

| |
|---|
| [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.md) |

## Constructors

| | |
|---|---|
| [EnvPath](-env-path.md) | [jvm]<br>constructor(path: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html))constructor(pathString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [isAbsolute](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/is-absolute.md) | [jvm]<br>open override val [EnvPath](index.md).[isAbsolute](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/is-absolute.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isEmpty](is-empty.md) | [jvm]<br>@get:Contract(pure = true)<br>val [isEmpty](is-empty.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [pathString](path-string.md) | [jvm]<br>val [pathString](path-string.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [toLocalPath](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/to-local-path.md) | [jvm]<br>open override fun [EnvPath](index.md).[toLocalPath](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/to-local-path.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt; |
| [toString](to-string.md) | [jvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
