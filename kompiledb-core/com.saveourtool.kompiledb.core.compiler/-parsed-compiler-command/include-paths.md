//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.compiler](../index.md)/[ParsedCompilerCommand](index.md)/[includePaths](include-paths.md)

# includePaths

[jvm]\
fun [includePaths](include-paths.md)(pathMapper: [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.md) = LocalPathMapper): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;&gt;

Converts the include paths to a local form using the [pathMapper](include-paths.md), normalizing them if necessary.

[jvm]\
val [includePaths](include-paths.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)&gt;&gt;

#### Parameters

jvm

| | |
|---|---|
| includePaths | the include paths. The keys are command-line switches such as `-I` or `-include`. The values are paths to include files or directories. |
