# kompiledb

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build](https://github.com/saveourtool/kompiledb/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/saveourtool/kompiledb/actions/workflows/build.yml?query=branch%3Amaster)
[![Dependencies](https://github.com/saveourtool/kompiledb/actions/workflows/dependencies.yml/badge.svg?branch=master)](https://github.com/saveourtool/kompiledb/actions/workflows/dependencies.yml?query=branch%3Amaster)

_Kotlin_ bindings to [_JSON Compilation Database_](http://clang.llvm.org/docs/JSONCompilationDatabase.html) Format.

This library is inspired by tools such as [nickdiego/compiledb](https://github.com/nickdiego/compiledb) and [rizsotto/Bear](https://github.com/rizsotto/Bear).

See the [project website](https://saveourtool.github.io/kompiledb/) for documentation and APIs.

## Features

 - Can read and write `compile_commands.json`.
 - No dependencies (except for [google/gson](https://github.com/google/gson)).
   The core library is JSON engine agnostic, so it's easy to add support for a
   different JSON back-end, such as [FasterXML/jackson](https://github.com/FasterXML/jackson)
   or [Kotlin/kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization).
 - Support for pluggable path translation between the local and the build
   environments (such as [_Cygwin_](https://www.cygwin.com) or
   [_WSL_](https://github.com/Microsoft/WSL)).
   Built-in path mapper for [_MSys_](https://www.msys2.org).
 - Command-line parser for _GCC_ and compatibles, such as _Clang_:
   + [response file](https://gcc.gnu.org/wiki/Response_Files) expansion;
   + support for 'single'- and "double"-quoted arguments (also within response files);
   + include paths (`-I`, `-include`, `-isystem` and so on) recognized;
   + defined (`-D`) and undefined (`-U`) preprocessor macros recognized;
   + language (C, C++) and standard (`c++17`, `gnu++14`) recognized;
   + `-nostdinc`, `-nostdinc++` and similar switches recognized.

## Releases

The latest release is available from both _GitHub Packages_ and _Maven Central_.
For _GitHub Packages_, the repository can be added as follows.

For `build.gradle.kts`:

```kotlin
repositories {
    maven {
        name = "saveourtool/kompiledb"
        url = uri("https://maven.pkg.github.com/saveourtool/kompiledb")
        content {
            includeGroup("com.saveourtool.kompiledb")
        }
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}
```

For `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        maven {
            name = "saveourtool/kompiledb"
            url = uri("https://maven.pkg.github.com/saveourtool/kompiledb")
            content {
                includeGroup("com.saveourtool.kompiledb")
            }
            credentials {
                username = providers.gradleProperty("gpr.user").orNull
                    ?: System.getenv("GITHUB_ACTOR")
                password = providers.gradleProperty("gpr.key").orNull
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
```

Then add the dependency as usual:

```kotlin
dependencies {
    implementation("com.saveourtool.kompiledb:kompiledb-gson:1.0.0")
}
```

## Other JSON formats

If your build system produces a JSON different from the compilation database
format, you can preprocess it with the [`jq`](https://github.com/jqlang/jq)
utility.

Example input:

```json
{
    "compiler_info": [
        {
            "compiler_name": "/usr/bin/gcc",
            "environment": [
                "PWD=/proc/self/cwd",
                "LANG=C.UTF-8",
                "JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8",
                "SHELL=/bin/bash",
                "SHLVL=9",
                "HISTSIZE=10000",
                "LD_PRELOAD=",
                "GCC_COLORS=error=01;31:warning=01;35:note=01;36:caret=01;32:locus=01:quote=01"
            ],
            "uuid": "8a3117fde10aa7cdc517ffa313174af5",
            "version": "4.2.1",
            "machine": "x86_64-pc-linux-gnu",
            "bits": "-m64",
            "system_include": [
                "/usr/include"
            ],
            "pre_define_macros": [
                "-DDEBUG=1",
                "-D__GNUC_MINOR__=2",
                "-D__GNUC_PATCHLEVEL__=1",
                "-D__GNUC_STDC_INLINE__=1",
                "-D__GNUC__=4"
            ]
        },
        {
            "compiler_name": "/usr/bin/clang",
            "environment": [
                "PWD=/proc/self/cwd",
                "LANG=C.UTF-8",
                "JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8",
                "SHELL=/bin/bash",
                "SHLVL=9",
                "HISTSIZE=10000",
                "LD_PRELOAD=",
                "GCC_COLORS=error=01;31:warning=01;35:note=01;36:caret=01;32:locus=01:quote=01"
            ],
            "uuid": "cfc0dbb9c58d9ee084ee0bec7cf69c25",
            "version": "4.2.1",
            "machine": "x86_64-redhat-linux-gnu",
            "bits": "-m32",
            "system_include": [
                "/usr/include"
            ],
            "pre_define_macros": [
                "-DDEBUG=1",
                "-D__GNUC_MINOR__=2",
                "-D__GNUC_PATCHLEVEL__=1",
                "-D__GNUC_STDC_INLINE__=1",
                "-D__GNUC__=4"
            ]
        }
    ],
    "compilation_unit": [
        {
            "arguments": [
                "gcc",
                "-c",
                "file1.c"
            ],
            "compiler": "/usr/bin/gcc",
            "directory": "/path/to/project",
            "file": [
                "/path/to/project/file1.c"
            ],
            "id": "23ba91c4f1ae11edb2e4fa163e78cf7a",
            "language": "C_LANG",
            "object": [
                "/path/to/project/file1.o"
            ],
            "timestamp": 1683996285773040,
            "original_command_id": "21f3529cf1ae11edb2e4fa163e78cf7a",
            "uuid": "cfc0dbb9c58d9ee084ee0bec7cf69c25",
            "head_include": [],
            "preprocess_file": [
                "/path/to/project/file1.i"
            ]
        },
        {
            "arguments": [
                "clang",
                "-c",
                "file2.c"
            ],
            "compiler": "/usr/bin/clang",
            "directory": "/path/to/project",
            "file": [
                "/path/to/project/file2.c"
            ],
            "id": "23bc7a02f1ae11edb2e4fa163e78cf7a",
            "language": "C_LANG",
            "object": [],
            "timestamp": 1683996379854618,
            "original_command_id": "21f3529ef1ae11edb2e4fa163e78cf7a",
            "uuid": "d15a90fcd7c5a199fbccab08440fd12e",
            "head_include": [],
            "preprocess_file": [
                "/path/to/project/file2.i"
            ]
        }
    ]
}
```

Running the file through the following filter:

```bash
jq '.compilation_unit | map(.file = .file[0] | .output = .object[0] | del(.compiler, .id, .language, .timestamp, .original_command_id, .uuid, .head_include, .preprocess_file, .preprocess_status, .object)) | del(..|nulls)' <example.json >compile_commands.json
```

&mdash; will result in the `compile_commands.json` of the following content:

```json
[
  {
    "arguments": [
      "gcc",
      "-c",
      "file1.c"
    ],
    "directory": "/path/to/project",
    "file": "/path/to/project/file1.c",
    "output": "/path/to/project/file1.o"
  },
  {
    "arguments": [
      "clang",
      "-c",
      "file2.c"
    ],
    "directory": "/path/to/project",
    "file": "/path/to/project/file2.c"
  }
]
```
