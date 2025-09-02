
import org.gradle.internal.extensions.core.extra
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.parcelize)
}

android {
    namespace = "tw.com.deathhit.feature.plant_list"
    compileSdk = rootProject.extra["compileSdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        val javaVersion = rootProject.extra["javaVersion"] as JavaVersion

        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlin {
        compilerOptions {
            jvmTarget = rootProject.extra["kotlinJvmTarget"] as JvmTarget
        }
    }
}

dependencies {
    implementation(project(":core:sunflower_clone_ui"))
    implementation(project(":domain:sunflower_clone"))

    //Androidx KTX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)

    //Coroutine
    implementation(libs.jetbrains.koltin.coroutine)
    androidTestImplementation(libs.jetbrains.koltin.coroutine.test)

    //Glide
    ksp(libs.glide.compiler)
    implementation(libs.glide)

    //Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt)

    //Paging
    implementation(libs.paging.runtime)
    androidTestImplementation(libs.paging.test)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}