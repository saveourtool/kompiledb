---
title: div
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io](../index.html)/[PathMapperScope](index.html)/[div](div.html)



# div



[jvm]\




@Contract(pure = true)



open operator fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[div](div.html)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)



Resolves the given [other](div.html) path against this path.



This operator is a shortcut for the [EnvPath.resolve](resolve.html) function.



#### Return



the resulting path.



#### Parameters


jvm

| | |
|---|---|
| other | the path to resolve against this path. |



#### See also


| |
|---|
| [PathMapperScope.resolve](resolve.html) |




[jvm]\




@Contract(pure = true)



open operator fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[div](div.html)(other: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;



Resolves the given [other](div.html) path against this path.



#### Return



the resulting path.



#### Parameters


jvm

| | |
|---|---|
| other | the path to resolve against this path. |




