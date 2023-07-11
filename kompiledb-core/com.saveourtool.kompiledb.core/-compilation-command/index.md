---
title: CompilationCommand
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core](../index.html)/[CompilationCommand](index.html)



# CompilationCommand

data class [CompilationCommand](index.html)(val directory: [EnvPath](../-env-path/index.html), val file: [EnvPath](../-env-path/index.html), val arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), val command: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792)? = null, val output: [EnvPath](../-env-path/index.html)? = null)

A [*JSON Compilation Database*](https://clang.llvm.org/docs/JSONCompilationDatabase.html) entry.



Either [arguments](arguments.html) or [command](command.html) is required. [arguments](arguments.html) is preferred, as shell (un)escaping is a possible source of errors.



#### Parameters


jvm

| | |
|---|---|
| directory | the working directory of the compilation. All paths specified in the [command](command.html) or [file](file.html) fields must be either absolute or relative to this directory. |
| file | the main translation unit source processed by this compilation step. This is used by tools as the key into the compilation database. There can be multiple command objects for the same file, for example if the same source file is compiled with different configurations. |
| command | the compile command executed. After JSON unescaping, this must be a valid command to rerun the exact compilation step for the translation unit in the environment the build system uses. Parameters use shell quoting and shell escaping of quotes, with `"` and `\` being the only special characters. Shell expansion is not supported. |
| arguments | the compile command `argv` as list of strings. This should run the compilation step for the translation unit [file](file.html). `arguments[0]` should be the executable name, such as `clang++`. Arguments should not be escaped, but ready to pass to `execvp()`. |
| output | the name of the output created by this compilation step. This field is optional. It can be used to distinguish different processing modes of the same input file. |



#### See also


| |
|---|
| [CompilationDatabase](../-compilation-database/index.html) |


#### Throws


| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | if [file](file.html) is blank, or neither [command](command.html) nor [arguments](arguments.html) are specified. |


## Constructors


| | |
|---|---|
| [CompilationCommand](-compilation-command.html) | [jvm]<br>constructor(directory: [EnvPath](../-env-path/index.html), file: [EnvPath](../-env-path/index.html), arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), output: [EnvPath](../-env-path/index.html)? = null)<br>constructor(directory: [EnvPath](../-env-path/index.html), file: [EnvPath](../-env-path/index.html), command: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792), output: [EnvPath](../-env-path/index.html)? = null)<br>constructor(directory: [EnvPath](../-env-path/index.html), file: [EnvPath](../-env-path/index.html), arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792), command: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792)? = null, output: [EnvPath](../-env-path/index.html)? = null) |


## Properties


| Name | Summary |
|---|---|
| [arguments](arguments.html) | [jvm]<br>val [arguments](arguments.html): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792)<br>The compile command `argv` as list of strings. This should run the compilation step for the translation unit [file](file.html). `arguments[0]` should be the executable name, such as `clang++`. Arguments should not be escaped, but ready to pass to `execvp()`. |
| [command](command.html) | [jvm]<br>val [command](command.html): [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792)? = null<br>The compile command executed. After JSON unescaping, this must be a valid command to rerun the exact compilation step for the translation unit in the environment the build system uses. Parameters use shell quoting and shell escaping of quotes, with `"` and `\` being the only special characters. Shell expansion is not supported. |
| [directory](directory.html) | [jvm]<br>val [directory](directory.html): [EnvPath](../-env-path/index.html)<br>The working directory of the compilation. All paths specified in the [command](command.html) or [file](file.html) fields must be either absolute or relative to this directory. |
| [file](file.html) | [jvm]<br>val [file](file.html): [EnvPath](../-env-path/index.html)<br>The main translation unit source processed by this compilation step. This is used by tools as the key into the compilation database. There can be multiple command objects for the same file, for example if the same source file is compiled with different configurations. |
| [output](output.html) | [jvm]<br>val [output](output.html): [EnvPath](../-env-path/index.html)? = null<br>The name of the output created by this compilation step. This field is optional. It can be used to distinguish different processing modes of the same input file. |
| [parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.html) | [jvm]<br>open val [CompilationCommand](index.html).[parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.html): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792)<br>Splits the receiver into individual arguments. |

