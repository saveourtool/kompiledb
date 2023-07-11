---
title: EnvPath
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core](../index.html)/[EnvPath](index.html)



# EnvPath





@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)



value class [EnvPath](index.html)(val pathString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

The path in the target build environment.



Use [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.html) to convert this path to a local [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).



#### Parameters


jvm

| | |
|---|---|
| pathString | the string representation of this path. |



#### See also


| |
|---|
| [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.html) |


## Constructors


| | |
|---|---|
| [EnvPath](-env-path.html) | [jvm]<br>constructor(path: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html))constructor(pathString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |


## Types


| Name | Summary |
|---|---|
| [Companion](-companion/index.html) | [jvm]<br>object [Companion](-companion/index.html) |


## Properties


| Name | Summary |
|---|---|
| [isAbsolute](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/is-absolute.html) | [jvm]<br>open override val [EnvPath](index.html).[isAbsolute](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/is-absolute.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isEmpty](is-empty.html) | [jvm]<br>@get:Contract(pure = true)<br>val [isEmpty](is-empty.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [pathString](path-string.html) | [jvm]<br>val [pathString](path-string.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |


## Functions


| Name | Summary |
|---|---|
| [toLocalPath](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/to-local-path.html) | [jvm]<br>open override fun [EnvPath](index.html).[toLocalPath](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/to-local-path.html)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt; |
| [toString](to-string.html) | [jvm]<br>open override fun [toString](to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

