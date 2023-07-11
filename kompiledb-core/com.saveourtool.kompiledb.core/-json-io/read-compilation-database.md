//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core](../index.md)/[JsonIo](index.md)/[readCompilationDatabase](read-compilation-database.md)

# readCompilationDatabase

[jvm]\
abstract fun [Reader](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html).[readCompilationDatabase](read-compilation-database.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[CompilationDatabase](../-compilation-database/index.md)&gt;

Reads the content of a character stream as a compilation database.

**Note**:  It is the caller's responsibility to close this reader.

#### Return

either a compilation database, or a JSON syntax error.

#### Throws

| | |
|---|---|
| [IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) | in case an I/O error occurs while reading the content of a stream. |

[jvm]\
abstract fun [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html).[readCompilationDatabase](read-compilation-database.md)(charset: [Charset](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html) = UTF_8): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[CompilationDatabase](../-compilation-database/index.md)&gt;

Reads the content of a JSON file as a compilation database. If the receiver is a directory, an attempt is made to read the content of its child named `compile_commands.json`.

#### Receiver

either the compilation database JSON file, or a directory which contains a `compile_commands.json` file.

#### Return

either a compilation database, or a JSON syntax error.

#### Throws

| | |
|---|---|
| [IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) | in case an I/O error occurs while reading a file. |
