//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io.mappers](../index.md)/[LocalPathMapper](index.md)

# LocalPathMapper

[jvm]\
object [LocalPathMapper](index.md) : [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.md)

The path mapper suitable for a native local environment (no heuristics, no path translation).

## Properties

| Name | Summary |
|---|---|
| [fileSeparator](file-separator.md) | [jvm]<br>open override val [fileSeparator](file-separator.md): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) |
| [isAbsolute](is-absolute.md) | [jvm]<br>open override val [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[isAbsolute](is-absolute.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [toEnvironmentPath](to-environment-path.md) | [jvm]<br>open override fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[toEnvironmentPath](to-environment-path.md)(): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md) |
| [toLocalPath](to-local-path.md) | [jvm]<br>open override fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[toLocalPath](to-local-path.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt; |
