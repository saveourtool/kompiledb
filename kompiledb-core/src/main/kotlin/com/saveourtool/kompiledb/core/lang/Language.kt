package com.saveourtool.kompiledb.core.lang

/**
 * The language of the source code file, as passed to the compiler via
 * command-line switches such as `-xc` or `-xc++`.
 *
 * @see C
 * @see Cxx
 * @see Other
 */
sealed interface Language {
    /**
     * The language in the format `-x` switch accepts (_Clang_ and _GCC_).
     */
    val name: String

    companion object {
        /**
         * `-xc`.
         */
        internal const val LANG_C = "c"

        /**
         * `-xc++`.
         */
        internal const val LANG_CXX = "c++"

        /**
         * Converts [language] to a [Language] instance.
         */
        operator fun invoke(language: String): Language =
            when (language) {
                LANG_C -> C
                LANG_CXX -> Cxx
                else -> Other(language)
            }
    }
}
