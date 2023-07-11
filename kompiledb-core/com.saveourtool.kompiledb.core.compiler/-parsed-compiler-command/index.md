//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.compiler](../index.md)/[ParsedCompilerCommand](index.md)

# ParsedCompilerCommand

data class [ParsedCompilerCommand](index.md)(val projectRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), val directory: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), val file: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), val compiler: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), val language: [Language](../../com.saveourtool.kompiledb.core.lang/-language/index.md), val languageStandard: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val standardIncludePaths: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[StandardIncludePaths](../-standard-include-paths/index.md)&gt;, val includePaths: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)&gt;&gt;, val definedMacros: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val undefinedMacros: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), val ignoredArguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), val parseErrors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList())

The result of parsing a [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md) with a [CompilerCommandParser](../-compiler-command-parser/index.md)

#### Parameters

jvm

| | |
|---|---|
| projectRoot | the root of the project as the local file system path. |
| directory | the environment-specific path to the working directory of the compilation. All paths specified in the [file](file.md), [compiler](compiler.md), [includePaths](include-paths.md), [arguments](arguments.md) or [ignoredArguments](ignored-arguments.md) fields must be either absolute or relative to this directory. |
| file | the environment-specific path to the main translation unit source processed by this compilation step. |
| compiler | the environment-specific path to the compiler. |
| language | the language of the source file (C, C++, or other). |
| languageStandard | the language standard, such as `c++23`, `gnu++14`, or `iso9899:2018`. |
| standardIncludePaths | the standard include paths (those which don't need to be explicitly requested via `-I`). |
| includePaths | the include paths. The keys are command-line switches such as `-I` or `-include`. The values are paths to include files or directories. |
| definedMacros | the defined macros along with their values. |
| undefinedMacros | the undefined (cancelled) macros. |
| arguments | the compile command `argv` as list of strings, similar to [CompilationCommand.arguments](../../com.saveourtool.kompiledb.core/-compilation-command/arguments.md). The list may contain additional arguments as a result of response file expansion. |
| ignoredArguments | the [arguments](arguments.md) which are neither [includePaths](include-paths.md), nor [definedMacros](defined-macros.md), nor [undefinedMacros](undefined-macros.md) — in other words, those which don't fall into any of the &quot;interesting&quot; categories. |
| parseErrors | the list of parse errors encountered, if any. |

#### See also

| |
|---|
| [CompilationCommand](../../com.saveourtool.kompiledb.core/-compilation-command/index.md) |
| [CompilerCommandParser.parse](../-compiler-command-parser/parse.md) |

## Constructors

| | |
|---|---|
| [ParsedCompilerCommand](-parsed-compiler-command.md) | [jvm]<br>constructor(projectRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), directory: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), file: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), compiler: [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md), language: [Language](../../com.saveourtool.kompiledb.core.lang/-language/index.md), languageStandard: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, standardIncludePaths: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[StandardIncludePaths](../-standard-include-paths/index.md)&gt;, includePaths: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)&gt;&gt;, definedMacros: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, undefinedMacros: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), ignoredArguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), parseErrors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList()) |

## Properties

| Name | Summary |
|---|---|
| [arguments](arguments.md) | [jvm]<br>val [arguments](arguments.md): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835) |
| [compiler](compiler.md) | [jvm]<br>val [compiler](compiler.md): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md) |
| [definedMacros](defined-macros.md) | [jvm]<br>val [definedMacros](defined-macros.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [directory](directory.md) | [jvm]<br>val [directory](directory.md): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md) |
| [file](file.md) | [jvm]<br>val [file](file.md): [EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md) |
| [ignoredArguments](ignored-arguments.md) | [jvm]<br>val [ignoredArguments](ignored-arguments.md): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835) |
| [includePaths](include-paths.md) | [jvm]<br>val [includePaths](include-paths.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../../com.saveourtool.kompiledb.core/-env-path/index.md)&gt;&gt; |
| [language](language.md) | [jvm]<br>val [language](language.md): [Language](../../com.saveourtool.kompiledb.core.lang/-language/index.md) |
| [languageStandard](language-standard.md) | [jvm]<br>val [languageStandard](language-standard.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [parseErrors](parse-errors.md) | [jvm]<br>val [parseErrors](parse-errors.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [projectRoot](project-root.md) | [jvm]<br>val [projectRoot](project-root.md): [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html) |
| [standardIncludePaths](standard-include-paths.md) | [jvm]<br>val [standardIncludePaths](standard-include-paths.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[StandardIncludePaths](../-standard-include-paths/index.md)&gt; |
| [undefinedMacros](undefined-macros.md) | [jvm]<br>val [undefinedMacros](undefined-macros.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [includePaths](include-paths.md) | [jvm]<br>fun [includePaths](include-paths.md)(pathMapper: [PathMapper](../../com.saveourtool.kompiledb.core.io/-path-mapper/index.md) = LocalPathMapper): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html)&gt;&gt;<br>Converts the include paths to a local form using the [pathMapper](include-paths.md), normalizing them if necessary. |
