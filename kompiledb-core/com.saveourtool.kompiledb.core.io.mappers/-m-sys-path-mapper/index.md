//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.io.mappers](../index.md)/[MSysPathMapper](index.md)

# MSysPathMapper

class [MSysPathMapper](index.md)(mSysRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)) : [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.md)

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
| [MSysPathMapper](-m-sys-path-mapper.md) | [jvm]<br>constructor(mSysRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)) |

## Properties

| Name | Summary |
|---|---|
| [fileSeparator](file-separator.md) | [jvm]<br>open override val [fileSeparator](file-separator.md): [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) |
| [isAbsolute](is-absolute.md) | [jvm]<br>open override val [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[isAbsolute](is-absolute.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [toEnvironmentPath](to-environment-path.md) | [jvm]<br>open override fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[toEnvironmentPath](to-environment-path.md)(): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)<br>The effect is the same as the result of running `cygpath -u`. |
| [toLocalPath](to-local-path.md) | [jvm]<br>open override fun [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md).[toLocalPath](to-local-path.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;<br>The effect is the same as the result of running `cygpath -w`. |
