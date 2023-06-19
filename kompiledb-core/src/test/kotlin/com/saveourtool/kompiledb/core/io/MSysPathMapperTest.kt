package com.saveourtool.kompiledb.core.io

import com.saveourtool.kompiledb.core.EnvPath
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.paths.shouldBeADirectory
import io.kotest.matchers.paths.shouldBeAbsolute
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.string.shouldMatch
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.div
import kotlin.io.path.name
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * @see MSysPathMapper
 */
class MSysPathMapperTest {
    private lateinit var pathMapper: PathMapper

    @BeforeTest
    fun checkIsWindows() {
        assumeTrue(System.getProperty("os.name").startsWith("Windows ")) {
            System.getProperty("os.name") + ' ' + System.getProperty("os.version")
        }
    }

    @BeforeTest
    fun setUpMapper(@TempDir mSysRoot: Path) {
        pathMapper = MSysPathMapper(mSysRoot)
    }

    @Test
    fun `unc path should be absolute`() {
        with(pathMapper) {
            sequenceOf("""\\wsl$\Ubuntu""", "//wsl$/Ubuntu")
                .map(::EnvPath)
                .forEach { uncPath ->
                    uncPath.isAbsolute.shouldBeTrue()
                }
        }
    }

    @Test
    fun `unc path mapped to environment`() {
        with(pathMapper) {
            sequenceOf("""\\wsl$\Debian""", "//wsl$/Debian")
                .map(::EnvPath)
                .forEach { uncPath ->
                    uncPath.toLocalPath()
                        .shouldBeSuccess()
                        .toEnvironmentPath()
                        .pathString shouldBeIn arrayOf("//wsl$/Debian", "//wsl$/Debian/")
                }
        }
    }

    @Test
    fun `relative path should remain relative when mapped to local`() {
        with(pathMapper) {
            sequenceOf("""foo\bar""", "foo/bar")
                .map(::EnvPath)
                .forEach { relative ->
                    relative.isAbsolute.shouldBeFalse()

                    val local = relative.toLocalPath().shouldBeSuccess()
                    local.isAbsolute.shouldBeFalse()
                    local shouldBeEqual Path("foo") / "bar"
                }
        }
    }

    @Test
    fun `relative path should remain relative when mapped to environment`() {
        with(pathMapper) {
            sequenceOf("foo", "bar", "..")
                .map(::Path)
                .flatMap { parent ->
                    sequenceOf("foo", "bar", "..").map { child ->
                        parent / child
                    }
                }
                .forEach { local ->
                    local.toEnvironmentPath().isAbsolute.shouldBeFalse()
                }
        }
    }

    /**
     * A _Windows_ path without a drive letter, such as `\Windows`, should have
     * the drive letter when converted to a local path. This is the behaviour\
     * consistent with `cygpath`:
     *
     * ```
     * cygpath -w '\foo\bar'
     * C:\foo\bar
     * ```
     *
     * The drive letter prepended to the path string is the drive letter of the
     * current path (not necessarily `C:`).
     */
    @Test
    fun `windows path without drive letter`() {
        with(pathMapper) {
            val absolute = EnvPath("""\path\without\drive\letter""")

            absolute.isAbsolute.shouldBeTrue()

            absolute.toLocalPath()
                .shouldBeSuccess()
                .shouldBeAbsolute()
        }
    }

    @Test
    fun `windows path`() {
        with(pathMapper) {
            sequenceOf("""C:\""", "C:/")
                .map(::EnvPath)
                .forEach { windowsRoot ->
                    windowsRoot.isAbsolute.shouldBeTrue()

                    windowsRoot.toLocalPath().shouldBeSuccess() shouldBeEqual Path("""C:\""")
                }
        }
    }

    @Test
    fun `root should be absolute`() {
        with(pathMapper) {
            val mSysRoot = EnvPath("/")

            mSysRoot.isAbsolute.shouldBeTrue()
        }
    }

    @Test
    fun `root should be an existing directory`() {
        with(pathMapper) {
            val mSysRoot = EnvPath("/")

            mSysRoot.toLocalPath().shouldBeSuccess().apply {
                shouldBeAbsolute()
                shouldBeADirectory()
            }
        }
    }

    @Test
    fun `child of msys root mapped to environment`() {
        with(pathMapper) {
            val localRoot = EnvPath("/").toLocalPath().shouldBeSuccess()

            sequenceOf(
                localRoot / "bin",
                localRoot / ".." / localRoot.name / "bin",
            ).forEach { path ->
                path.toEnvironmentPath().pathString shouldBeEqual "/bin"
            }
        }
    }

    @Test
    fun `msys root mapped to environment`() {
        with(pathMapper) {
            val localRoot = EnvPath("/").toLocalPath().shouldBeSuccess()

            sequenceOf(
                localRoot,
                localRoot / ".." / localRoot.name,
            ).forEach { path ->
                path.toEnvironmentPath().pathString shouldBeEqual "/"
            }
        }
    }

    @Test
    fun `parent of msys root mapped to environment`() {
        with(pathMapper) {
            val localRoot = EnvPath("/").toLocalPath().shouldBeSuccess()

            sequenceOf(
                localRoot / "..",
            ).forEach { path ->
                path.toEnvironmentPath().pathString shouldMatch Regex("""^/[A-Za-z]/.*$""")
            }
        }
    }
}
