//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[CompilationCommand](index.md)/[command](command.md)

# command

[jvm]\
val [command](command.md): [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.md#1075615255%2FClasslikes%2F-937334835)? = null

The compile command executed. After JSON unescaping, this must be a valid command to rerun the exact compilation step for the translation unit in the environment the build system uses. Parameters use shell quoting and shell escaping of quotes, with `"` and `\` being the only special characters. Shell expansion is not supported.
