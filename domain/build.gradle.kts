import org.gradle.internal.extensions.core.extra

plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    val javaVersion = rootProject.extra["javaVersion"] as JavaVersion

    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

dependencies {
    //Coroutine
    implementation(libs.jetbrains.koltin.coroutine)

    //Paging
    api(libs.paging.common)
}