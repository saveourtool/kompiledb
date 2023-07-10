@file:Suppress("UnstableApiUsage")

plugins {
    `java-test-fixtures`
    id("com.saveourtool.kompiledb.kotlin-configuration")
    id("com.saveourtool.kompiledb.testing-configuration")
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.kotlin.stdlib.jdk8)
    testFixturesApi(libs.kotest.assertions.core)
    testFixturesApi(libs.kotest.assertions.json)
}

tasks.withType<Test> {
    filter {
        includeTestsMatching("com.saveourtool.kompiledb.core.*")
        isFailOnNoMatchingTests = true
    }
}
