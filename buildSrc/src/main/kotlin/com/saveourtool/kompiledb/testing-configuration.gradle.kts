package com.saveourtool.kompiledb

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

plugins {
    `java-library`
}

internal val libs = the<LibrariesForLibs>()

testing {
    suites {
        @Suppress("UNUSED_VARIABLE", "UnstableApiUsage")
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(libs.versions.kotlin)
        }
    }
}

tasks.withType<Test> {
    testLogging {
        showStandardStreams = true
        showCauses = true
        showExceptions = true
        showStackTraces = true
        exceptionFormat = FULL
        events("passed", "skipped")
    }
}
