import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(rootProject.extra["kotlinJvmTarget"] as String)
        }
    }

    sourceSets {
        androidMain.dependencies {

        }

        androidInstrumentedTest.dependencies {
            //Coroutine
            implementation(libs.jetbrains.koltin.coroutine.test)

            implementation(libs.androidx.junit)
            implementation(libs.androidx.espresso.core)
        }

        androidUnitTest.dependencies {
            implementation(libs.junit)
        }

        commonMain.dependencies {
            //Coroutine
            implementation(libs.jetbrains.koltin.coroutine)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "tw.com.deathhit.core.sunflower_clone_database_kmp"
    compileSdk = rootProject.extra["compileSdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

}