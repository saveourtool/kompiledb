---
title: ParsedCompilerCommand
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.compiler](../index.html)/[ParsedCompilerCommand](index.html)



# ParsedCompilerCommand

data class [ParsedCompilerCommand](index.html)(val projectRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), val directory: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html), val file: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html), val compiler: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html), val language: [Language](../../com.saveourtool.kompiledb.core.lang/-language/index.html), val languageStandard: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val standardIncludePaths: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[StandardIncludePaths](../-standard-include-paths/index.html)&gt;, val includePaths: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)&gt;&gt;, val definedMacros: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val undefinedMacros: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), val ignoredArguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), val parseErrors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList())

The result of parsing a [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html) with a [CompilerCommandParser](../-compiler-command-parser/index.html)



#### Parameters


jvm

| | |
|---|---|
| projectRoot | the root of the project as the local file system path. |
| directory | the environment-specific path to the working directory of the compilation. All paths specified in the [file](file.html), [compiler](compiler.html), [includePaths](include-paths.html), [arguments](arguments.html) or [ignoredArguments](ignored-arguments.html) fields must be either absolute or relative to this directory. |
| file | the environment-specific path to the main translation unit source processed by this compilation step. |
| compiler | the environment-specific path to the compiler. |
| language | the language of the source file (C, C++, or other). |
| languageStandard | the language standard, such as `c++23`, `gnu++14`, or `iso9899:2018`. |
| standardIncludePaths | the standard include paths (those which don't need to be explicitly requested via `-I`). |
| includePaths | the include paths. The keys are command-line switches such as `-I` or `-include`. The values are paths to include files or directories. |
| definedMacros | the defined macros along with their values. |
| undefinedMacros | the undefined (cancelled) macros. |
| arguments | the compile command `argv` as list of strings, similar to [CompilationCommand.arguments](../../com.saveourtool.kompiledb.core/-compilation-command/arguments.html). The list may contain additional arguments as a result of response file expansion. |
| ignoredArguments | the [arguments](arguments.html) which are neither [includePaths](include-paths.html), nor [definedMacros](defined-macros.html), nor [undefinedMacros](undefined-macros.html) — in other words, those which don't fall into any of the &quot;interesting&quot; categories. |
| parseErrors | the list of parse errors encountered, if any. |



#### See also


| |
|---|
| [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.html) |
| [CompilerCommandParser.parse](../-compiler-command-parser/parse.html) |


## Constructors


| | |
|---|---|
| [ParsedCompilerCommand](-parsed-compiler-command.html) | [jvm]<br>constructor(projectRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), directory: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html), file: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html), compiler: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html), language: [Language](../../com.saveourtool.kompiledb.core.lang/-language/index.html), languageStandard: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, standardIncludePaths: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[StandardIncludePaths](../-standard-include-paths/index.html)&gt;, includePaths: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)&gt;&gt;, definedMacros: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, undefinedMacros: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), ignoredArguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), parseErrors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList()) |


## Properties


| Name | Summary |
|---|---|
| [arguments](arguments.html) | [jvm]<br>val [arguments](arguments.html): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792) |
| [compiler](compiler.html) | [jvm]<br>val [compiler](compiler.html): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html) |
| [definedMacros](defined-macros.html) | [jvm]<br>val [definedMacros](defined-macros.html): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [directory](directory.html) | [jvm]<br>val [directory](directory.html): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html) |
| [file](file.html) | [jvm]<br>val [file](file.html): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html) |
| [ignoredArguments](ignored-arguments.html) | [jvm]<br>val [ignoredArguments](ignored-arguments.html): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792) |
| [includePaths](include-paths.html) | [jvm]<br>val [includePaths](include-paths.html): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.html)&gt;&gt; |
| [language](language.html) | [jvm]<br>val [language](language.html): [Language](../../com.saveourtool.kompiledb.core.lang/-language/index.html) |
| [languageStandard](language-standard.html) | [jvm]<br>val [languageStandard](language-standard.html): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [parseErrors](parse-errors.html) | [jvm]<br>val [parseErrors](parse-errors.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [projectRoot](project-root.html) | [jvm]<br>val [projectRoot](project-root.html): [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html) |
| [standardIncludePaths](standard-include-paths.html) | [jvm]<br>val [standardIncludePaths](standard-include-paths.html): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[StandardIncludePaths](../-standard-include-paths/index.html)&gt; |
| [undefinedMacros](undefined-macros.html) | [jvm]<br>val [undefinedMacros](undefined-macros.html): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |


## Functions


| Name | Summary |
|---|---|
| [includePaths](include-paths.html) | [jvm]<br>fun [includePaths](include-paths.html)(pathMapper: [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.html) = LocalPathMapper): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;&gt;<br>Converts the include paths to a local form using the [pathMapper](include-paths.html), normalizing them if necessary. |

