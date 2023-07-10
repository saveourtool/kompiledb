package com.saveourtool.kompiledb

import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

internal val libs = the<LibrariesForLibs>()

dependencies {
    api(libs.kotlin.stdlib.jdk8)
}

java {
    withJavadocJar()
    withSourcesJar()
}

kotlin.jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of("8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
