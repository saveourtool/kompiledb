//[kompiledb-core](../../index.md)/[com.saveourtool.kompiledb.core.io](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Arg](index.md#-198843362%2FClasslikes%2F-937334835) | [jvm]<br>typealias [Arg](index.md#-198843362%2FClasslikes%2F-937334835) = [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>An argument of a parsed command line. |
| [CommandLineParser](-command-line-parser/index.md) | [jvm]<br>interface [CommandLineParser](-command-line-parser/index.md) : [Function1](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-function1/index.html)&lt;[RawCommandLine](index.md#1075615255%2FClasslikes%2F-937334835), [ParsedArgs](index.md#1743527040%2FClasslikes%2F-937334835)&gt; <br>Command-line parser. |
| [ParsedArgs](index.md#1743527040%2FClasslikes%2F-937334835) | [jvm]<br>typealias [ParsedArgs](index.md#1743527040%2FClasslikes%2F-937334835) = [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Arg](index.md#-198843362%2FClasslikes%2F-937334835)&gt;<br>A parsed command line. |
| [PathMapper](-path-mapper/index.md) | [jvm]<br>interface [PathMapper](-path-mapper/index.md)<br>Transforms a path stored in a [CompilationCommand](../com.saveourtool.kompiledb.core/-compilation-command/index.md) instance to a JVM [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), and vice versa. |
| [PathMapperScope](-path-mapper-scope/index.md) | [jvm]<br>interface [PathMapperScope](-path-mapper-scope/index.md)<br>A set of extensions to [EnvPath](../com.saveourtool.kompiledb.core/-env-path/index.md) which are not dependent on the implementation-specific details of a [PathMapper](-path-mapper/index.md). |
| [RawCommandLine](index.md#1075615255%2FClasslikes%2F-937334835) | [jvm]<br>typealias [RawCommandLine](index.md#1075615255%2FClasslikes%2F-937334835) = [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A raw command line (possibly containing quotation marks and escape characters). |
