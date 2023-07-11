---
title: StandardIncludePaths
---
//[kompiledb-core](../../../index.html)/[com.saveourtool.kompiledb.core.compiler](../index.html)/[StandardIncludePaths](index.html)



# StandardIncludePaths



[jvm]\
enum [StandardIncludePaths](index.html) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[StandardIncludePaths](index.html)&gt; 

Standard include paths (those which don't need to be explicitly requested via `-I`). These include paths may be both language- and compiler-dependent.



## Entries


| | |
|---|---|
| [STANDARD_C_LIBRARY](-s-t-a-n-d-a-r-d_-c_-l-i-b-r-a-r-y/index.html) | [jvm]<br>[STANDARD_C_LIBRARY](-s-t-a-n-d-a-r-d_-c_-l-i-b-r-a-r-y/index.html)<br>The headers of the standard C library, such as `stdio.h`, usually located under `/usr/include`. |
| [STANDARD_CXX_LIBRARY](-s-t-a-n-d-a-r-d_-c-x-x_-l-i-b-r-a-r-y/index.html) | [jvm]<br>[STANDARD_CXX_LIBRARY](-s-t-a-n-d-a-r-d_-c-x-x_-l-i-b-r-a-r-y/index.html)<br>The headers of the standard C++ library, such as `iostream`, usually located under `/usr/include/c++`. |
| [COMPILER_BUILTIN_INCLUDES](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.html) | [jvm]<br>[COMPILER_BUILTIN_INCLUDES](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.html)<br>Compiler-specific builtin directories for include files, such as `/usr/lib/llvm-11/lib/clang/11.0.1/include` or `/usr/lib/gcc/x86_64-linux-gnu/10/include`. |


## Properties


| Name | Summary |
|---|---|
| [name](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.html#-372974862%2FProperties%2F-204370792) | [jvm]<br>val [name](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.html#-372974862%2FProperties%2F-204370792): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ordinal](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.html#-739389684%2FProperties%2F-204370792) | [jvm]<br>val [ordinal](-c-o-m-p-i-l-e-r_-b-u-i-l-t-i-n_-i-n-c-l-u-d-e-s/index.html#-739389684%2FProperties%2F-204370792): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |


## Functions


| Name | Summary |
|---|---|
| [valueOf](value-of.html) | [jvm]<br>fun [valueOf](value-of.html)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [StandardIncludePaths](index.html)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.html) | [jvm]<br>fun [values](values.html)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StandardIncludePaths](index.html)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |

