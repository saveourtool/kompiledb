package com.saveourtool.kompiledb.core.io

import com.saveourtool.kompiledb.core.EnvPath
import java.nio.file.Path
import com.saveourtool.kompiledb.core.CompilationCommand

/**
 * Transforms a path stored in a [CompilationCommand] instance to a JVM [Path].
 */
fun interface PathMapper : (EnvPath) -> Result<Path>
