plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //Coroutine
    val coroutineVersion = "1.8.1"
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

    //Paging
    val pagingVersion = "3.3.0"
    api("androidx.paging:paging-common-ktx:$pagingVersion")
}