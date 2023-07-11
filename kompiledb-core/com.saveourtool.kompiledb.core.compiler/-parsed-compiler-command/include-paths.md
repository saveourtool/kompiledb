---
title: includePaths
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.compiler](../index.html)/[ParsedCompilerCommand](index.html)/[includePaths](include-paths.html)



# includePaths



[jvm]\
fun [includePaths](include-paths.html)(pathMapper: [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.html) = LocalPathMapper): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;&gt;



Converts the include paths to a local form using the [pathMapper](include-paths.html), normalizing them if necessary.





[jvm]\
val [includePaths](include-paths.html): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)&gt;&gt;



#### Parameters


jvm

| | |
|---|---|
| includePaths | the include paths. The keys are command-line switches such as `-I` or `-include`. The values are paths to include files or directories. |




