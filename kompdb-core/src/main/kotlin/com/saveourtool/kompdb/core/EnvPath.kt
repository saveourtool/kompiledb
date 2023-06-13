package com.saveourtool.kompdb.core

import java.nio.file.Path
import kotlin.io.path.Path
import java.nio.file.InvalidPathException
import kotlin.io.path.pathString

/**
 * The path in the target build environment.
 *
 * @param path the string representation of this path.
 */
@JvmInline
value class EnvPath(val path: String) {
    constructor(path: Path) : this(path.pathString)

    /**
     * @return this path as a local `Path` instance.
     * @throws InvalidPathException if the path string cannot be converted to a
     *   `Path`.
     */
    fun toLocalPath(): Path =
        Path(path)
}
