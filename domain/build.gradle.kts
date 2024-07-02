plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //Coroutine
    implementation(libs.jetbrains.koltin.coroutine)

    //Paging
    val pagingVersion = "3.3.0"
    api("androidx.paging:paging-common-ktx:$pagingVersion")
}