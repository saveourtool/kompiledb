package com.saveourtool.kompiledb.core.io

import com.saveourtool.kompiledb.core.EnvPath
import java.io.File.pathSeparatorChar
import java.nio.file.InvalidPathException
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.pathString

/**
 * The path mapper suitable for a native local environment (no heuristics, no
 * path translation).
 */
object LocalPathMapper : PathMapper {
    override val fileSeparator: Char
        get() =
            pathSeparatorChar

    override val EnvPath.isAbsolute: Boolean
        get() =
            toLocalPath()
                .map(Path::isAbsolute)
                .getOrDefault(false)

    /**
     * @return this path as a local `Path` instance, or a failure if
     *   [InvalidPathException] was thrown.
     */
    override fun EnvPath.toLocalPath(): Result<Path> =
        runCatching {
            Path(pathString)
        }

    override fun Path.toEnvironmentPath(): EnvPath =
        EnvPath(pathString)
}
