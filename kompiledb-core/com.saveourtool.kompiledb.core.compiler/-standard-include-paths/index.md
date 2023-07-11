//[kompiledb-core](../../../index.md)/[com.saveourtool.kompiledb.core.compiler](../index.md)/[StandardIncludePaths](index.md)

# StandardIncludePaths

[jvm]\
enum [StandardIncludePaths](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[StandardIncludePaths](index.md)&gt; 

Standard include paths (those which don't need to be explicitly requested via `-I`). These include paths may be both language- and compiler-dependent.

## Entries

| | |
|---|---|
| [STANDARD_C_LIBRARY](-s-t-a-n-d-a-r-d_-c_-l-i-b-r-a-r-y/index.md) | [jvm]<br>[STANDARD_C_LIBRARY](-s-t-a-n-d-a-r-d_-c_-l-i-b-r-a-r-y/index.md)<br>The headers of the standard C library, such as `stdio.h`, usually located under `/usr/include`. |
| [STANDARD_CXX_LIBRARY](-s-t-a-n-d-a-r-d_-c-x-x_-l-i-b-r-a-r-y/index.md) | [jvm]<br>[STANDARD_CXX_LIBRARY](-s-t-a-n-d-a-r-d_-c-x-x_-l-i-b-r-a-r-y/index.md)<br>The headers of the standard C++ library, such as `iostream`, usually located under `/usr/include/c++`. |
| [COMPILER_BUILTIN_INCLUDES](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.md) | [jvm]<br>[COMPILER_BUILTIN_INCLUDES](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.md)<br>Compiler-specific builtin directories for include files, such as `/usr/lib/llvm-11/lib/clang/11.0.1/include` or `/usr/lib/gcc/x86_64-linux-gnu/10/include`. |

## Properties

| Name | Summary |
|---|---|
| [name](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.md#-372974862%2FProperties%2F-937334835) | [jvm]<br>val [name](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.md#-372974862%2FProperties%2F-937334835): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ordinal](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.md#-739389684%2FProperties%2F-937334835) | [jvm]<br>val [ordinal](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.md#-739389684%2FProperties%2F-937334835): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [jvm]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [StandardIncludePaths](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | [jvm]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StandardIncludePaths](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
