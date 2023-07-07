package com.saveourtool.kompiledb.core.lang

import com.saveourtool.kompiledb.core.lang.Language.Companion.LANG_C

/**
 * The C language.
 *
 * @see Cxx
 * @see Other
 */
object C : Language {
    override val name = LANG_C

    override fun toString(): String =
        name
}
