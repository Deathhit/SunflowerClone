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
    val coroutineVersion = "1.7.3"
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    //Paging
    val pagingVersion = "3.2.1"
    api("androidx.paging:paging-common-ktx:$pagingVersion")
}