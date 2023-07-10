package com.saveourtool.kompiledb

import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

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
