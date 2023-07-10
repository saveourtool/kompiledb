package com.saveourtool.kompiledb.core.compiler

import com.saveourtool.kompiledb.core.CompilationCommand
import com.saveourtool.kompiledb.core.EnvPath
import com.saveourtool.kompiledb.core.io.ParsedArgs
import com.saveourtool.kompiledb.core.io.PathMapper
import com.saveourtool.kompiledb.core.io.PathMapperScope.Companion.withPathMapper
import com.saveourtool.kompiledb.core.io.mappers.LocalPathMapper
import com.saveourtool.kompiledb.core.lang.Language
import java.nio.file.Path
import kotlin.collections.Map.Entry

/**
 * The result of parsing a [CompilationCommand] with a [CompilerCommandParser]
 *
 * @param projectRoot the root of the project as the local file system path.
 * @param directory the environment-specific path to the working directory of
 *   the compilation.
 *   All paths specified in the [file], [compiler], [includePaths], [arguments]
 *   or [ignoredArguments] fields must be either absolute or relative to this
 *   directory.
 * @param file the environment-specific path to the main translation unit source
 *   processed by this compilation step.
 * @param compiler the environment-specific path to the compiler.
 * @param language the language of the source file (C, C++, or other).
 * @param languageStandard the language standard, such as `c++23`, `gnu++14`, or
 *   `iso9899:2018`.
 * @param standardIncludePaths the standard include paths (those which don't
 *   need to be explicitly requested via `-I`).
 * @param includePaths the include paths.
 *   The keys are command-line switches such as `-I` or `-include`.
 *   The values are paths to include files or directories.
 * @param definedMacros the defined macros along with their values.
 * @param undefinedMacros the undefined (cancelled) macros.
 * @param arguments the compile command `argv` as list of strings, similar to
 *   [CompilationCommand.arguments]. The list may contain additional arguments
 *   as a result of response file expansion.
 * @param ignoredArguments the [arguments] which are neither [includePaths], nor
 *   [definedMacros], nor [undefinedMacros] &mdash; in other words, those which
 *   don't fall into any of the "interesting" categories.
 * @param parseErrors the list of parse errors encountered, if any.
 * @see CompilationCommand
 * @see CompilerCommandParser.parse
 */
data class ParsedCompilerCommand(
    val projectRoot: Path,
    val directory: EnvPath,
    val file: EnvPath,
    val compiler: EnvPath,
    val language: Language,
    val languageStandard: String?,
    val standardIncludePaths: Set<StandardIncludePaths>,
    val includePaths: Map<String, List<EnvPath>>,
    val definedMacros: Map<String, String>,
    val undefinedMacros: List<String>,
    val arguments: ParsedArgs,
    val ignoredArguments: ParsedArgs,
    val parseErrors: List<String> = emptyList(),
) {
    /**
     * Converts the include paths to a local form using the [pathMapper],
     * normalizing them if necessary.
     */
    fun includePaths(pathMapper: PathMapper = LocalPathMapper): Map<String, List<Path>> =
        withPathMapper(pathMapper) {
            includePaths.asSequence()
                .flatMapValues { _, includePath ->
                    /*
                     * Resolve from right to left.
                     */
                    projectRoot / (directory / includePath)
                }
                .filterValueIsSuccess()
                .mapValues { (_, includePath) ->
                    includePath.normalize()
                }
                .groupBy(
                    keySelector = Pair<String, *>::first,
                    valueTransform = Pair<*, Path>::second,
                )
        }

    private companion object {
        private fun <K, V, R> Sequence<Entry<K, Iterable<V>>>.flatMapValues(transform: (K, V) -> R): Sequence<Pair<K, R>> =
            flatMap { (key, values) ->
                values.asSequence().map { value ->
                    key to transform(key, value)
                }
            }

        private fun <K, V, R> Sequence<Pair<K, V>>.mapValues(transform: (Pair<K, V>) -> R): Sequence<Pair<K, R>> =
            map { entry ->
                entry.first to transform(entry)
            }

        private fun <K, V> Sequence<Pair<K, Result<V>>>.filterValueIsSuccess(): Sequence<Pair<K, V>> =
            sequence {
                forEach { (key, valueOrNull) ->
                    valueOrNull.fold(
                        onSuccess = { value ->
                            yield(key to value)
                        },
                        onFailure = {},
                    )
                }
            }
    }
}
