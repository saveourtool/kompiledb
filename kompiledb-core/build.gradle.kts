plugins {
    `java-test-fixtures`
    id("com.saveourtool.kompiledb.maven-repo-configuration")
    id("com.saveourtool.kompiledb.kotlin-configuration")
    id("com.saveourtool.kompiledb.testing-configuration")
}

dependencies {
    testFixturesApi(libs.kotest.assertions.core)
    testFixturesApi(libs.kotest.assertions.json)
}

tasks.withType<Test> {
    filter {
        includeTestsMatching("com.saveourtool.kompiledb.core.*")
        isFailOnNoMatchingTests = true
    }
}
