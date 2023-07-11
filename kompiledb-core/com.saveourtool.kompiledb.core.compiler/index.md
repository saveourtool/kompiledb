---
title: com.saveourtool.kompiledb.core.compiler
---
//[kompiledb-core](../../index.html)/[com.saveourtool.kompiledb.core.compiler](index.html)



# Package-level declarations



## Types


| Name | Summary |
|---|---|
| [CompilerCommandParser](-compiler-command-parser/index.html) | [jvm]<br>fun interface [CompilerCommandParser](-compiler-command-parser/index.html)<br>Parses a [CompilationCommand](../com.saveourtool.kompiledb.core/-compilation-command/index.html) in a compiler-specific way. |
| [ParsedCompilerCommand](-parsed-compiler-command/index.html) | [jvm]<br>data class [ParsedCompilerCommand](-parsed-compiler-command/index.html)(val projectRoot: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html), val directory: [EnvPath](../com.saveourtool.kompiledb.core/-env-path/index.html), val file: [EnvPath](../com.saveourtool.kompiledb.core/-env-path/index.html), val compiler: [EnvPath](../com.saveourtool.kompiledb.core/-env-path/index.html), val language: [Language](../com.saveourtool.kompiledb.core.lang/-language/index.html), val languageStandard: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val standardIncludePaths: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[StandardIncludePaths](-standard-include-paths/index.html)&gt;, val includePaths: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EnvPath](../com.saveourtool.kompiledb.core/-env-path/index.html)&gt;&gt;, val definedMacros: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val undefinedMacros: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, val arguments: [ParsedArgs](../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), val ignoredArguments: [ParsedArgs](../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), val parseErrors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = emptyList())<br>The result of parsing a [CompilationCommand](../com.saveourtool.kompiledb.core/-compilation-command/index.html) with a [CompilerCommandParser](-compiler-command-parser/index.html) |
| [StandardIncludePaths](-standard-include-paths/index.html) | [jvm]<br>enum [StandardIncludePaths](-standard-include-paths/index.html) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[StandardIncludePaths](-standard-include-paths/index.html)&gt; <br>Standard include paths (those which don't need to be explicitly requested via `-I`). These include paths may be both language- and compiler-dependent. |

