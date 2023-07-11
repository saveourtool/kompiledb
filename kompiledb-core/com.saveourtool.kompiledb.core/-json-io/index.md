//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[JsonIo](index.md)

# JsonIo

[jvm]\
interface [JsonIo](index.md)

## Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | [jvm]<br>object [Factory](-factory/index.md)<br>The factory used to create instances of [JsonIo](index.md). |

## Functions

| Name | Summary |
|---|---|
| [readCompilationDatabase](read-compilation-database.md) | [jvm]<br>abstract fun [Reader](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html).[readCompilationDatabase](read-compilation-database.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[CompilationDatabase](../-compilation-database/index.md)&gt;<br>Reads the content of a character stream as a compilation database.<br>[jvm]<br>abstract fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[readCompilationDatabase](read-compilation-database.md)(charset: [Charset](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html) = UTF_8): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[CompilationDatabase](../-compilation-database/index.md)&gt;<br>Reads the content of a JSON file as a compilation database. If the receiver is a directory, an attempt is made to read the content of its child named `compile_commands.json`. |
| [toCompilationCommand](to-compilation-command.md) | [jvm]<br>abstract fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[toCompilationCommand](to-compilation-command.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[CompilationCommand](../-compilation-command/index.md)&gt;<br>De-serializes a compilation command from this JSON string. |
| [toCompilationDatabase](to-compilation-database.md) | [jvm]<br>abstract fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[toCompilationDatabase](to-compilation-database.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[CompilationDatabase](../-compilation-database/index.md)&gt;<br>De-serializes a compilation database from this JSON string. |
| [toJson](to-json.md) | [jvm]<br>abstract fun [CompilationCommand](../-compilation-command/index.md).[toJson](to-json.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Converts this compilation command to JSON.<br>[jvm]<br>abstract fun [CompilationDatabase](../-compilation-database/index.md).[toJson](to-json.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Converts this compilation database to JSON. |
