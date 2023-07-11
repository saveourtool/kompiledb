//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io.mappers](../index.md)/[LocalPathMapper](index.md)/[toLocalPath](to-local-path.md)

# toLocalPath

[jvm]\
open override fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[toLocalPath](to-local-path.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;

#### Return

this path as a local `Path` instance, or a failure if [InvalidPathException](https://docs.oracle.com/javase/8/docs/api/java/nio/file/InvalidPathException.html) was thrown.
