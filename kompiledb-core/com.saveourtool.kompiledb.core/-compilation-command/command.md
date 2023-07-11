---
title: command
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core](../index.html)/[CompilationCommand](index.html)/[command](command.html)



# command



[jvm]\
val [command](command.html): [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792)? = null



The compile command executed. After JSON unescaping, this must be a valid command to rerun the exact compilation step for the translation unit in the environment the build system uses. Parameters use shell quoting and shell escaping of quotes, with `"` and `\` being the only special characters. Shell expansion is not supported.




