import org.gradle.internal.extensions.core.extra
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    val javaVersion = rootProject.extra["javaVersion"] as JavaVersion

    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

kotlin {
    compilerOptions {
        jvmTarget = rootProject.extra["kotlinJvmTarget"] as JvmTarget
    }
}

dependencies {
    //Coroutine
    implementation(libs.jetbrains.koltin.coroutine)

    //Paging
    api(libs.paging.common)
}