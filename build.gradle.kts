@file:Suppress("UnstableApiUsage")

import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    testImplementation(libs.kotest.assertions.core)
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
        includeTestsMatching("com.saveourtool.kompdb.*")
    }
}
