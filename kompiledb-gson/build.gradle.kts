plugins {
    id("com.saveourtool.kompiledb.maven-repo-configuration")
    id("com.saveourtool.kompiledb.kotlin-configuration")
    id("com.saveourtool.kompiledb.testing-configuration")
    id("com.saveourtool.kompiledb.publishing-configuration")
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
