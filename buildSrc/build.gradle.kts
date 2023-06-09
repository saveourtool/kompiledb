plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.gradle.kotlin.dsl.plugins)
}
