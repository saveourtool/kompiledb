---
title: resolve
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io](../index.html)/[PathMapperScope](index.html)/[resolve](resolve.html)



# resolve



[jvm]\




@Contract(pure = true)



open infix fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[resolve](resolve.html)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)





@Contract(pure = true)



open infix fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[resolve](resolve.html)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;



Resolve the given [other](resolve.html) path against this path.



If the [other](resolve.html) parameter is an absolute path then this method trivially returns [other](resolve.html). If [other](resolve.html) is an [*empty path*](../../com.saveourtool.kompiledb.core/-env-path/is-empty.html) then this method trivially returns this path. Otherwise this method considers this path to be a directory and resolves the given path against this path.



#### Return



the resulting path.



#### Parameters


jvm

| | |
|---|---|
| other | the path to resolve against this path. |




