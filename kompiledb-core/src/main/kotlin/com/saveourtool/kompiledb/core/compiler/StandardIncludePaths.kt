package com.saveourtool.kompiledb.core.compiler

/**
 * Standard include paths (those which don't need to be explicitly requested via
 * `-I`). These include paths may be both language- and compiler-dependent.
 */
enum class StandardIncludePaths {
    /**
     * The headers of the standard C library, such as `stdio.h`, usually located
     * under `/usr/include`.
     */
    STANDARD_C_LIBRARY,

    /**
     * The headers of the standard C++ library, such as `iostream`, usually
     * located under `/usr/include/c++`.
     */
    STANDARD_CXX_LIBRARY,

    /**
     * Compiler-specific builtin directories for include files, such as
     * `/usr/lib/llvm-11/lib/clang/11.0.1/include` or
     * `/usr/lib/gcc/x86_64-linux-gnu/10/include`.
     */
    COMPILER_BUILTIN_INCLUDES,
}
