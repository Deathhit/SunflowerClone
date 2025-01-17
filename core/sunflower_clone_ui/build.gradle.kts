import org.gradle.internal.extensions.core.extra

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

android {
    namespace = "tw.com.deathhit.core.sunflower_clone_ui"
    compileSdk = rootProject.extra["compileSdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
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

    kotlinOptions {
        jvmTarget = rootProject.extra["kotlinJvmTarget"] as String
    }
}

dependencies {
    //Appcompat
    api(libs.androidx.appcompat)

    //Compose
    val composeBom = platform(libs.compose.bom)
    api(composeBom)
    androidTestApi(composeBom)

    //Compose-Material 3
    api(libs.compose.material3)

    //Compose-Preview
    api(libs.compose.ui.preview)
    debugApi(libs.compose.ui)

    //Compose-View Binding
    api(libs.compose.ui.viewbinding)

    //Constraint Layout
    api(libs.constraintlayout)

    //Constraint Layout-Compose
    api(libs.constraintlayout.compose)

    //Glide-Compose
    api(libs.glide.compose)

    //Material Design
    api(libs.material)

    //Recycler View
    api(libs.recyclerview)

    //View Pager 2
    api(libs.viewpager2)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}