//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io](../index.md)/[PathMapper](index.md)

# PathMapper

interface [PathMapper](index.md)

Transforms a path stored in a [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md) instance to a JVM [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), and vice versa.

#### Inheritors

| |
|---|
| [LocalPathMapper](../../com.saveourtool.kompiledb.core.io.mappers/-local-path-mapper/index.md) |
| [MSysPathMapper](../../com.saveourtool.kompiledb.core.io.mappers/-m-sys-path-mapper/index.md) |

## Properties

| Name | Summary |
|---|---|
| [fileSeparator](file-separator.md) | [jvm]<br>abstract val [fileSeparator](file-separator.md): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) |
| [isAbsolute](is-absolute.md) | [jvm]<br>abstract val [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[isAbsolute](is-absolute.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [toEnvironmentPath](to-environment-path.md) | [jvm]<br>abstract fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[toEnvironmentPath](to-environment-path.md)(): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md) |
| [toLocalPath](to-local-path.md) | [jvm]<br>abstract fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[toLocalPath](to-local-path.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt; |
