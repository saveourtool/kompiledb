package com.saveourtool.kompiledb.core.io

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import java.nio.file.Path

/**
 * Transforms a path stored in a [CompilationCommand] instance to a JVM [Path],
 * and vice versa.
 */
interface PathMapper {
    val fileSeparator: Char

    val EnvPath.isAbsolute: Boolean

    fun EnvPath.toLocalPath(): Result<Path>

    fun Path.toEnvironmentPath(): EnvPath
}
