@file:Suppress("UnstableApiUsage")

plugins {
    id("com.saveourtool.kompiledb.kotlin-configuration")
    id("com.saveourtool.kompiledb.testing-configuration")
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":kompiledb-core"))
    implementation(libs.google.gson)
    testImplementation(testFixtures(project(":kompiledb-core")))
}

tasks.withType<Test> {
    filter {
        includeTestsMatching("com.saveourtool.kompiledb.gson.*")
        isFailOnNoMatchingTests = true
    }
}
