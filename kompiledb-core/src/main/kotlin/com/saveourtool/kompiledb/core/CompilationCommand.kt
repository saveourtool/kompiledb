package com.saveourtool.kompiledb.core

import org.intellij.lang.annotations.Language

/**
 * A [_JSON Compilation Database_](https://clang.llvm.org/docs/JSONCompilationDatabase.html)
 * entry.
 *
 * Either [arguments] or [command] is required. [arguments] is preferred, as
 * shell (un)escaping is a possible source of errors.
 *
 * @param directory the working directory of the compilation. All paths
 *   specified in the [command] or [file] fields must be either absolute or
 *   relative to this directory.
 * @param file the main translation unit source processed by this compilation
 *   step. This is used by tools as the key into the compilation database. There
 *   can be multiple command objects for the same file, for example if the same
 *   source file is compiled with different configurations.
 * @param command the compile command executed. After JSON unescaping, this must
 *   be a valid command to rerun the exact compilation step for the translation
 *   unit in the environment the build system uses. Parameters use shell quoting
 *   and shell escaping of quotes, with `"` and `\` being the only special
 *   characters. Shell expansion is not supported.
 * @param arguments the compile command `argv` as list of strings. This should
 *   run the compilation step for the translation unit [file]. `arguments[0]`
 *   should be the executable name, such as `clang++`. Arguments should not be
 *   escaped, but ready to pass to `execvp()`.
 * @param output the name of the output created by this compilation step. This
 *   field is optional. It can be used to distinguish different processing modes
 *   of the same input file.
 * @throws IllegalArgumentException if [file] is blank, or neither [command] nor
 *   [arguments] are specified.
 * @see CompilationDatabase
 */
data class CompilationCommand(
    /**
     * The working directory of the compilation. All paths specified in the
     * [command] or [file] fields must be either absolute or relative to this
     * directory.
     */
    val directory: EnvPath,

    /**
     * The main translation unit source processed by this compilation step. This
     * is used by tools as the key into the compilation database. There can be
     * multiple command objects for the same file, for example if the same
     * source file is compiled with different configurations.
     */
    val file: EnvPath,

    /**
     * The compile command `argv` as list of strings. This should run the
     * compilation step for the translation unit [file]. `arguments[0]` should
     * be the executable name, such as `clang++`. Arguments should not be
     * escaped, but ready to pass to `execvp()`.
     */
    val arguments: List<String>,

    /**
     * The compile command executed. After JSON unescaping, this must be a valid
     * command to rerun the exact compilation step for the translation unit in
     * the environment the build system uses. Parameters use shell quoting and
     * shell escaping of quotes, with `"` and `\` being the only special
     * characters. Shell expansion is not supported.
     */
    @Language("sh")
    val command: String? = null,

    /**
     * The name of the output created by this compilation step. This field is
     * optional. It can be used to distinguish different processing modes of the
     * same input file.
     */
    val output: EnvPath? = null,
) {
    /**
     * @throws IllegalArgumentException if [file] is blank, or [arguments] are
     *   empty.
     */
    constructor(
        directory: EnvPath,
        file: EnvPath,
        arguments: List<String>,
        output: EnvPath? = null,
    ) : this(
        directory,
        file,
        arguments,
        command = null,
        output,
    )

    /**
     * @throws IllegalArgumentException if either [file] or [command] is blank.
     */
    constructor(
        directory: EnvPath,
        file: EnvPath,
        @Language("sh") command: String,
        output: EnvPath? = null,
    ) : this(
        directory,
        file,
        arguments = emptyList(),
        command,
        output,
    )

    init {
        require(arguments.isNotEmpty() || !command.isNullOrBlank()) {
            "Either `arguments` or `command` is required"
        }
        require(file.path.isNotBlank()) {
            "`file` shouldn't be blank"
        }
    }
}
