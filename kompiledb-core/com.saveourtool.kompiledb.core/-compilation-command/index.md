//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[CompilationCommand](index.md)

# CompilationCommand

data class [CompilationCommand](index.md)(val directory: [EnvPath](../-env-path/index.md), val file: [EnvPath](../-env-path/index.md), val arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), val command: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835)? = null, val output: [EnvPath](../-env-path/index.md)? = null)

A [*JSON Compilation Database*](https://clang.llvm.org/docs/JSONCompilationDatabase.html) entry.

Either [arguments](arguments.md) or [command](command.md) is required. [arguments](arguments.md) is preferred, as shell (un)escaping is a possible source of errors.

#### Parameters

jvm

| | |
|---|---|
| directory | the working directory of the compilation. All paths specified in the [command](command.md) or [file](file.md) fields must be either absolute or relative to this directory. |
| file | the main translation unit source processed by this compilation step. This is used by tools as the key into the compilation database. There can be multiple command objects for the same file, for example if the same source file is compiled with different configurations. |
| command | the compile command executed. After JSON unescaping, this must be a valid command to rerun the exact compilation step for the translation unit in the environment the build system uses. Parameters use shell quoting and shell escaping of quotes, with `"` and `\` being the only special characters. Shell expansion is not supported. |
| arguments | the compile command `argv` as list of strings. This should run the compilation step for the translation unit [file](file.md). `arguments[0]` should be the executable name, such as `clang++`. Arguments should not be escaped, but ready to pass to `execvp()`. |
| output | the name of the output created by this compilation step. This field is optional. It can be used to distinguish different processing modes of the same input file. |

#### See also

| |
|---|
| [CompilationDatabase](../-compilation-database/index.md) |

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | if [file](file.md) is blank, or neither [command](command.md) nor [arguments](arguments.md) are specified. |

## Constructors

| | |
|---|---|
| [CompilationCommand](-compilation-command.md) | [jvm]<br>constructor(directory: [EnvPath](../-env-path/index.md), file: [EnvPath](../-env-path/index.md), arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), output: [EnvPath](../-env-path/index.md)? = null)<br>constructor(directory: [EnvPath](../-env-path/index.md), file: [EnvPath](../-env-path/index.md), command: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835), output: [EnvPath](../-env-path/index.md)? = null)<br>constructor(directory: [EnvPath](../-env-path/index.md), file: [EnvPath](../-env-path/index.md), arguments: [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835), command: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835)? = null, output: [EnvPath](../-env-path/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [arguments](arguments.md) | [jvm]<br>val [arguments](arguments.md): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835)<br>The compile command `argv` as list of strings. This should run the compilation step for the translation unit [file](file.md). `arguments[0]` should be the executable name, such as `clang++`. Arguments should not be escaped, but ready to pass to `execvp()`. |
| [command](command.md) | [jvm]<br>val [command](command.md): [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835)? = null<br>The compile command executed. After JSON unescaping, this must be a valid command to rerun the exact compilation step for the translation unit in the environment the build system uses. Parameters use shell quoting and shell escaping of quotes, with `"` and `\` being the only special characters. Shell expansion is not supported. |
| [directory](directory.md) | [jvm]<br>val [directory](directory.md): [EnvPath](../-env-path/index.md)<br>The working directory of the compilation. All paths specified in the [command](command.md) or [file](file.md) fields must be either absolute or relative to this directory. |
| [file](file.md) | [jvm]<br>val [file](file.md): [EnvPath](../-env-path/index.md)<br>The main translation unit source processed by this compilation step. This is used by tools as the key into the compilation database. There can be multiple command objects for the same file, for example if the same source file is compiled with different configurations. |
| [output](output.md) | [jvm]<br>val [output](output.md): [EnvPath](../-env-path/index.md)? = null<br>The name of the output created by this compilation step. This field is optional. It can be used to distinguish different processing modes of the same input file. |
| [parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.md) | [jvm]<br>open val [CompilationCommand](index.md).[parsedArguments](../../com.saveourtool.kompiledb.core.io/-command-line-parser/parsed-arguments.md): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.md#1743527040%2FClasslikes%2F-937334835)<br>Splits the receiver into individual arguments. |
