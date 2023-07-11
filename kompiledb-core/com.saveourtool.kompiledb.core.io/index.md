---
title: com.saveourtool.kompiledb.core.io
---
//[kompiledb-core](../../index.html)/[com.saveourtool.kompiledb.core.io](index.html)



# Package-level declarations



## Types


| Name | Summary |
|---|---|
| [Arg](index.html#-198843362%2FClasslikes%2F-204370792) | [jvm]<br>typealias [Arg](index.html#-198843362%2FClasslikes%2F-204370792) = [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>An argument of a parsed command line. |
| [CommandLineParser](-command-line-parser/index.html) | [jvm]<br>interface [CommandLineParser](-command-line-parser/index.html) : [Function1](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-function1/index.html)&lt;[RawCommandLine](index.html#1075615255%2FClasslikes%2F-204370792), [ParsedArgs](index.html#1743527040%2FClasslikes%2F-204370792)&gt; <br>Command-line parser. |
| [ParsedArgs](index.html#1743527040%2FClasslikes%2F-204370792) | [jvm]<br>typealias [ParsedArgs](index.html#1743527040%2FClasslikes%2F-204370792) = [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Arg](index.html#-198843362%2FClasslikes%2F-204370792)&gt;<br>A parsed command line. |
| [PathMapper](-path-mapper/index.html) | [jvm]<br>interface [PathMapper](-path-mapper/index.html)<br>Transforms a path stored in a [CompilationCommand](../com.saveourtool.kompiledb.core/-compilation-command/index.html) instance to a JVM [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), and vice versa. |
| [PathMapperScope](-path-mapper-scope/index.html) | [jvm]<br>interface [PathMapperScope](-path-mapper-scope/index.html)<br>A set of extensions to [EnvPath](../com.saveourtool.kompiledb.core/-env-path/index.html) which are not dependent on the implementation-specific details of a [PathMapper](-path-mapper/index.html). |
| [RawCommandLine](index.html#1075615255%2FClasslikes%2F-204370792) | [jvm]<br>typealias [RawCommandLine](index.html#1075615255%2FClasslikes%2F-204370792) = [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A raw command line (possibly containing quotation marks and escape characters). |

