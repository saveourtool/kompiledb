---
title: LocalPathMapper
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io.mappers](../index.html)/[LocalPathMapper](index.html)



# LocalPathMapper



[jvm]\
object [LocalPathMapper](index.html) : [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.html)

The path mapper suitable for a native local environment (no heuristics, no path translation).



## Properties


| Name | Summary |
|---|---|
| [fileSeparator](file-separator.html) | [jvm]<br>open override val [fileSeparator](file-separator.html): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) |
| [isAbsolute](is-absolute.html) | [jvm]<br>open override val [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[isAbsolute](is-absolute.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |


## Functions


| Name | Summary |
|---|---|
| [toEnvironmentPath](to-environment-path.html) | [jvm]<br>open override fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[toEnvironmentPath](to-environment-path.html)(): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html) |
| [toLocalPath](to-local-path.html) | [jvm]<br>open override fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[toLocalPath](to-local-path.html)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt; |

