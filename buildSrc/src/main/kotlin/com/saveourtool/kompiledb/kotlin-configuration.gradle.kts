package com.saveourtool.kompiledb

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
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
