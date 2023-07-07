package com.saveourtool.kompiledb.core.lang

/**
 * Neither C nor C++ (e.g.: ObjC).
 *
 * @param name the language in the format `-x` switch accepts (_Clang_ and
 *   _GCC_).
 * @see C
 * @see Cxx
 */
internal data class Other(override val name: String) : Language {
    override fun toString(): String =
        name
}
