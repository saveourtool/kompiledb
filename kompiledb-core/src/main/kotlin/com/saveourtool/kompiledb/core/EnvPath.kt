package com.saveourtool.kompiledb.core

import com.saveourtool.kompiledb.core.io.PathMapper
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.pathString

/**
 * The path in the target build environment.
 *
 * Use [PathMapper] to convert this path to a local [Path].
 *
 * @param pathString the string representation of this path.
 * @see PathMapper
 */
@JvmInline
value class EnvPath(val pathString: String) {
    constructor(path: Path) : this(path.pathString)

    override fun toString(): String =
        pathString
}
