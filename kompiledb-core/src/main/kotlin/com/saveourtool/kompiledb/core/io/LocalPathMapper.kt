package com.saveourtool.kompiledb.core.io

import com.saveourtool.kompiledb.core.EnvPath
import java.nio.file.InvalidPathException
import java.nio.file.Path
import kotlin.io.path.Path

/**
 * The path mapper suitable for a native local environment (no heuristics, no
 * path translation).
 */
object LocalPathMapper : PathMapper {
    /**
     * @return this path as a local `Path` instance, or a failure if
     *   [InvalidPathException] was thrown.
     */
    override fun invoke(envPath: EnvPath): Result<Path> =
        runCatching {
            Path(envPath.path)
        }
}
