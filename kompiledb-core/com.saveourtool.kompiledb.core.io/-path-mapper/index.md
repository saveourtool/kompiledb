---
title: PathMapper
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io](../index.html)/[PathMapper](index.html)



# PathMapper

interface [PathMapper](index.html)

Transforms a path stored in a [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html) instance to a JVM [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), and vice versa.



#### Inheritors


| |
|---|
| [LocalPathMapper](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/index.html) |
| [MSysPathMapper](../../com.saveourtool.kompiledb.core.io.mappers/-m-sys-path-mapper/index.html) |


## Properties


| Name | Summary |
|---|---|
| [fileSeparator](file-separator.html) | [jvm]<br>abstract val [fileSeparator](file-separator.html): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) |
| [isAbsolute](is-absolute.html) | [jvm]<br>abstract val [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[isAbsolute](is-absolute.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |


## Functions


| Name | Summary |
|---|---|
| [toEnvironmentPath](to-environment-path.html) | [jvm]<br>abstract fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[toEnvironmentPath](to-environment-path.html)(): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html) |
| [toLocalPath](to-local-path.html) | [jvm]<br>abstract fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[toLocalPath](to-local-path.html)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt; |

