// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.google.dagger.hilt) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.jetbrains.kotlin.parcelize) apply false
}

// Shared configuration values.
val compileSdk by extra(35)
val javaVersion by extra(JavaVersion.VERSION_21)
val kotlinJvmTarget by extra("21")
val minSdk by extra(24)
val versionCode by extra(1)
val versionName by extra("1.0")

