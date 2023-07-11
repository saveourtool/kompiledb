---
title: MSysPathMapper
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io.mappers](../index.html)/[MSysPathMapper](index.html)



# MSysPathMapper

class [MSysPathMapper](index.html)(mSysRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)) : [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.html)

Path mapper for *MSys*.



Handles 4 types of paths:



- 
   relative paths, such as `path/to/file` or `path\to\file`;
- 
   *MSys*-style absolute paths, such as `/C/Windows`;
- 
   *Windows*-style absolute paths, such as `C:/Windows` or `C:\Windows`;
- 
   *Windows* UNC paths, such as `//wsl$/Debian` or `\\wsl$\Debian`;




#### Parameters


jvm

| | |
|---|---|
| mSysRoot | the root of MSys installation (run `cygpath -w /` to find out the value). |



## Constructors


| | |
|---|---|
| [MSysPathMapper](-m-sys-path-mapper.html) | [jvm]<br>constructor(mSysRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)) |


## Properties


| Name | Summary |
|---|---|
| [fileSeparator](file-separator.html) | [jvm]<br>open override val [fileSeparator](file-separator.html): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) |
| [isAbsolute](is-absolute.html) | [jvm]<br>open override val [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[isAbsolute](is-absolute.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |


## Functions


| Name | Summary |
|---|---|
| [toEnvironmentPath](to-environment-path.html) | [jvm]<br>open override fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[toEnvironmentPath](to-environment-path.html)(): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)<br>The effect is the same as the result of running `cygpath -u`. |
| [toLocalPath](to-local-path.html) | [jvm]<br>open override fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html).[toLocalPath](to-local-path.html)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;<br>The effect is the same as the result of running `cygpath -w`. |

