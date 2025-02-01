import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.cash.sqldelight)
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
            //SQLDelight
            implementation(libs.sqldelight.android.driver)
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

            //Paging
            implementation(libs.paging.common)

            //SQLDelight
            implementation(libs.sqldelight.coroutine.ktx)
            implementation(libs.sqldelight.paging3.ktx)
            implementation(libs.sqldelight.sqlite.driver)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        nativeMain.dependencies {
            //SQLDelight
            implementation(libs.sqldelight.native.driver)
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

sqldelight {
    databases {
        create("SunflowerCloneDatabase") {
            packageName.set("tw.com.deathhit.core.sunflower_clone_database_kmp")
        }
    }
}