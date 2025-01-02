plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    //Coroutine
    implementation(libs.jetbrains.koltin.coroutine)

    //Paging
    api(libs.paging.common)
}