---
title: invoke
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.io.parsers](../index.html)/[AbstractComandLineParser](index.html)/[invoke](invoke.html)



# invoke



[jvm]\
operator override fun [invoke](invoke.html)(rawCommandLine: [RawCommandLine](../../com.saveourtool.kompiledb.core.io/index.html#1075615255%2FClasslikes%2F-204370792)): [ParsedArgs](../../com.saveourtool.kompiledb.core.io/index.html#1743527040%2FClasslikes%2F-204370792)



Splits [rawCommandLine](invoke.html) into individual arguments.



#### Return



the compile command `argv` as a list of strings, in a form ready to be passed to `execvp()` (i.e. un-escaped).




