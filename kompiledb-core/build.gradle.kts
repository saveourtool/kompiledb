@file:Suppress("UnstableApiUsage")

import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-test-fixtures`
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.kotlin.stdlib.jdk8)
    testFixturesApi(libs.kotest.assertions.core)
    testFixturesApi(libs.kotest.assertions.json)
}

java {
    withJavadocJar()
    withSourcesJar()
}

kotlin.jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of("8"))
}

testing {
    suites {
        @Suppress("UNUSED_VARIABLE")
        val test by getting(JvmTestSuite::class) {
            useKotlinTest(libs.versions.kotlin)
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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

    filter {
        includeTestsMatching("com.saveourtool.kompiledb.core.*")
        isFailOnNoMatchingTests = true
    }
}
