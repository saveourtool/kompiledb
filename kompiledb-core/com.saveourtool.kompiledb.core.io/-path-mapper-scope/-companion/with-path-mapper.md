//[kompiledb-core](../../../../index.md)/[com.saveourtool.kompiledb.core.io](../../index.md)/[PathMapperScope](../index.md)/[Companion](index.md)/[withPathMapper](with-path-mapper.md)

# withPathMapper

[jvm]\
fun &lt;[R](with-path-mapper.md)&gt; [withPathMapper](with-path-mapper.md)(pathMapper: [PathMapper](../../-path-mapper/index.md), block: [PathMapperScope](../index.md).() -&gt; [R](with-path-mapper.md)): [R](with-path-mapper.md)

Calls the [block](with-path-mapper.md) with a [PathMapperScope](../index.md) as its receiver and returns its result.

#### Parameters

jvm

| | |
|---|---|
| pathMapper | the path mapper to produce the necessary [PathMapperScope](../index.md). |
