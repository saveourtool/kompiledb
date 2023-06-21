package com.saveourtool.kompiledb.core.io.mappers

import com.saveourtool.kompiledb.core.EnvPath
import com.saveourtool.kompiledb.core.io.PathMapper
import java.io.IOException
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.absolute
import kotlin.io.path.div
import kotlin.io.path.isSameFileAs
import kotlin.io.path.pathString
import kotlin.io.path.relativeTo
import java.io.File.separatorChar as localFileSeparator

/**
 * Path mapper for _MSys_.
 *
 * Handles 4 types of paths:
 *
 *  - relative paths, such as `path/to/file` or `path\to\file`;
 *  - _MSys_-style absolute paths, such as `/C/Windows`;
 *  - _Windows_-style absolute paths, such as `C:/Windows` or `C:\Windows`;
 *  - _Windows_ UNC paths, such as `//wsl$/Debian` or `\\wsl$\Debian`;
 *
 * @param mSysRoot the root of MSys installation (run `cygpath -w /` to find out the
 *   value).
 */
class MSysPathMapper(private val mSysRoot: Path) : PathMapper {
    override val fileSeparator: Char
        get() =
            SLASH

    override val EnvPath.isAbsolute: Boolean
        get() =
            isAbsoluteUnixPath || isAbsoluteWindowsPath || isUncPath

    /**
     * The effect is the same as the result of running `cygpath -w`.
     */
    override fun EnvPath.toLocalPath(): Result<Path> =
        runCatching {
            val localPath = pathString.backslashify()

            when {
                isUncPath -> Path(localPath)

                isAbsoluteUnixPath -> mSysRoot / localPath.substring(1)

                isAbsoluteWindowsPath -> Path(localPath).absolute()

                else -> Path(localPath)
            }
        }

    /**
     * The effect is the same as the result of running `cygpath -u`.
     */
    override fun Path.toEnvironmentPath(): EnvPath {
        return when {
            isAbsolute -> when {
                isMSysRoot -> return ROOT

                mSysRoot.isParentOf(this) -> SLASH + relativeTo(mSysRoot).pathString.slashify()

                isUncPath -> pathString.slashify()

                else -> mSysPathString.slashify()
            }

            else -> pathString.slashify()
        }.let(::EnvPath)
    }

    private val Path.isMSysRoot: Boolean
        get() =
            isSameFileAsSafe(mSysRoot)

    /**
     * @throws IllegalStateException if the receiver is a UNC path.
     */
    private val Path.mSysPathString: String
        get() {
            check(isAbsolute) {
                "Not absolute: $pathString"
            }

            return "$BACKSLASH$driveLetter$BACKSLASH" + relativeTo(root).pathString
        }

    /**
     * @throws IllegalStateException if the receiver is a UNC path.
     */
    private val Path.driveLetter: Char
        get() {
            check(isAbsolute) {
                "Not absolute: $pathString"
            }

            val root = root?.pathString

            check(root != null && root.length == 3) {
                """The root of "$pathString" is not of the expected length: $root"""
            }

            check(root[1] == COLON && root[2] == BACKSLASH) {
                """The root of "$pathString" is doesn't match the expected pattern: $root"""
            }

            return root[0]
        }

    private fun String.slashify(): String =
        replace(localFileSeparator, fileSeparator)

    private fun String.backslashify(): String =
        replace(fileSeparator, localFileSeparator)

    private companion object {
        private const val COLON = ':'

        private const val SLASH = '/'

        private const val BACKSLASH = '\\'

        private const val UNC_PATH_PREFIX_WINDOWS = """\\"""

        private val UNC_PATH_PREFIXES: Array<out String> = arrayOf("//", UNC_PATH_PREFIX_WINDOWS)

        private val ROOT = EnvPath(SLASH.toString())

        private val Path.isUncPath: Boolean
            get() =
                pathString.startsWith(UNC_PATH_PREFIX_WINDOWS)

        private val EnvPath.isUncPath: Boolean
            get() =
                UNC_PATH_PREFIXES.any(pathString::startsWith)

        private val EnvPath.isAbsoluteUnixPath: Boolean
            get() =
                pathString.startsWith(SLASH) && !isUncPath

        private val EnvPath.isAbsoluteWindowsPath: Boolean
            get() =
                isAbsoluteWindowsPathWithDriveLetter || isAbsoluteWindowsPathWithoutDriveLetter

        private val EnvPath.isAbsoluteWindowsPathWithDriveLetter: Boolean
            get() =
                pathString.length >= 3
                        && pathString[0].isWindowsDriveLetter
                        && pathString[1] == COLON
                        && pathString[2] in sequenceOf(SLASH, BACKSLASH)

        private val EnvPath.isAbsoluteWindowsPathWithoutDriveLetter: Boolean
            get() =
                pathString.isNotEmpty()
                        && pathString[0] == BACKSLASH
                        && !isUncPath

        private val Char.isWindowsDriveLetter: Boolean
            get() =
                this in 'A'..'Z' ||
                        this in 'a'..'z'

        private val Path.normalizedAbsolutePath: Path
            get() =
                absolute().normalize()

        private fun Path.isSameFileAsSafe(other: Path): Boolean =
            try {
                isSameFileAs(other)
            } catch (_: IOException) {
                normalizedAbsolutePath == other.normalizedAbsolutePath
            }

        private fun Path.isParentOf(other: Path): Boolean {
            val otherParent = other.normalize().parent ?: return false

            val self = normalize()
            return self == otherParent || self.isParentOf(otherParent)
        }
    }
}
