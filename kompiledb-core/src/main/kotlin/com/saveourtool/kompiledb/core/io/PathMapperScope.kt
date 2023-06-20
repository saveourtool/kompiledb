package com.saveourtool.kompiledb.core.io

import com.saveourtool.kompiledb.core.EnvPath
import org.jetbrains.annotations.Contract
import java.nio.file.Path
import kotlin.Result.Companion.success
import kotlin.io.path.div

/**
 * A set of extensions to [EnvPath] which are not dependent on the
 * implementation-specific details of a [PathMapper].
 */
interface PathMapperScope {
    /**
     * The path mapper which performs path translation.
     */
    val pathMapper: PathMapper

    /**
     * Resolve the given [other] path against this path.
     *
     * If the [other] parameter is an absolute path then this method trivially
     * returns [other]. If [other] is an [_empty path_][EnvPath.isEmpty] then
     * this method trivially returns this path. Otherwise this method considers
     * this path to be a directory and resolves the given path against this
     * path.
     *
     * @param other the path to resolve against this path.
     * @return the resulting path.
     */
    @Contract(pure = true)
    fun EnvPath.resolve(other: EnvPath): EnvPath =
        with(pathMapper) {
            when {
                other.isEmpty -> this@resolve
                other.isAbsolute -> other
                pathString.endsWith(fileSeparator) -> EnvPath(pathString + other.pathString)
                else -> EnvPath(pathString + fileSeparator + other.pathString)
            }
        }

    /**
     * Resolve the given [other] path against this path.
     *
     * If the [other] parameter is an absolute path then this method trivially
     * returns [other]. If [other] is an [_empty path_][EnvPath.isEmpty] then
     * this method trivially returns this path. Otherwise this method considers
     * this path to be a directory and resolves the given path against this
     * path.
     *
     * @param other the path to resolve against this path.
     * @return the resulting path.
     */
    @Contract(pure = true)
    fun Path.resolve(other: EnvPath): Result<Path> =
        with(pathMapper) {
            when {
                other.isEmpty -> this@resolve
                other.isAbsolute -> return other.toLocalPath()
                else -> this@resolve / other.pathString
            }
        }.let { localPath ->
            success(localPath)
        }

    /**
     * Resolves the given [other] path against this path.
     *
     * This operator is a shortcut for the [EnvPath.resolve] function.
     *
     * @param other the path to resolve against this path.
     * @return the resulting path.
     * @see EnvPath.resolve
     */
    @Contract(pure = true)
    operator fun EnvPath.div(other: EnvPath): EnvPath =
        resolve(other)

    /**
     * Resolves the given [other] path against this path.
     *
     * @param other the path to resolve against this path.
     * @return the resulting path.
     */
    @Contract(pure = true)
    operator fun Path.div(other: EnvPath): Result<Path> =
        resolve(other)
}
