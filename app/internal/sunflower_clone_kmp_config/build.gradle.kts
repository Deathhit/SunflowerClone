import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.google.devtools.ksp)
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
            //Gson
            implementation(libs.gson)

            //Hilt
            implementation(libs.hilt)

            //Hilt-Work Manager
            api(libs.hilt.work)

            //Work Manager
            implementation(libs.work)
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
    namespace = "tw.com.deathhit.app.internal.sunflower_clone_kmp_config"
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
    implementation(project(":core:sunflower_clone_database_kmp"))
    implementation(project(":core:unsplash_api"))
    implementation(project(":data:garden_planting_kmp"))
    implementation(project(":data:photo_kmp"))
    implementation(project(":data:plant_kmp"))
    implementation(project(":domain"))

    //Hilt
    ksp(libs.hilt.compiler)

    //Hilt-Work Manager
    ksp(libs.hilt.work.compiler)
}