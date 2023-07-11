//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.compiler](../index.md)/[ParsedCompilerCommand](index.md)/[ParsedCompilerCommand](-parsed-compiler-command.md)

# ParsedCompilerCommand

[jvm]\
constructor(projectRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), directory: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), file: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), compiler: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), language: [Language](../../com.saveourtool.kompiledb.core.lang/-language/index.md), languageStandard: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, standardIncludePaths: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[StandardIncludePaths](../-standard-include-paths/index.md)&gt;, includePaths: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)&gt;&gt;, definedMacros: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, undefinedMacros: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), ignoredArguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), parseErrors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList())

#### Parameters

jvm

| | |
|---|---|
| projectRoot | the root of the project as the local file system path. |
| directory | the environment-specific path to the working directory of the compilation. All paths specified in the file, compiler, includePaths, arguments or ignoredArguments fields must be either absolute or relative to this directory. |
| file | the environment-specific path to the main translation unit source processed by this compilation step. |
| compiler | the environment-specific path to the compiler. |
| language | the language of the source file (C, C++, or other). |
| languageStandard | the language standard, such as `c++23`, `gnu++14`, or `iso9899:2018`. |
| standardIncludePaths | the standard include paths (those which don't need to be explicitly requested via `-I`). |
| includePaths | the include paths. The keys are command-line switches such as `-I` or `-include`. The values are paths to include files or directories. |
| definedMacros | the defined macros along with their values. |
| undefinedMacros | the undefined (cancelled) macros. |
| arguments | the compile command `argv` as list of strings, similar to [CompilationCommand.arguments](../../com.saveourtool.kompiledb.core/-compilation-command/arguments.md). The list may contain additional arguments as a result of response file expansion. |
| ignoredArguments | the arguments which are neither includePaths, nor definedMacros, nor undefinedMacros — in other words, those which don't fall into any of the &quot;interesting&quot; categories. |
| parseErrors | the list of parse errors encountered, if any. |
