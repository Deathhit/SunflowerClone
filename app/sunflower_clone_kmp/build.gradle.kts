import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.parcelize)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(rootProject.extra["kotlinJvmTarget"] as String)
        }
    }

    sourceSets {
        androidMain.dependencies {
            //todo migrate to kmp modules
            implementation(project(":domain"))
            implementation(project(":core:sunflower_clone_ui"))
            implementation(project(":feature:compose:gallery"))
            implementation(project(":feature:compose:navigation"))
            implementation(project(":feature:compose:plant_details"))

            //Androidx KTX
            implementation(libs.androidx.core.ktx)

            //Hilt
            implementation(libs.hilt)

            //Hilt-Navigation-Compose
            implementation(libs.hilt.navigation.compose)

            //Navigation-Compose
            implementation(libs.navigation.compose)

            //View Model
            implementation(libs.viewmodel.compose)
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
        namespace = "tw.com.deathhit.app.sunflower_clone_kmp"
        compileSdk = rootProject.extra["compileSdk"] as Int

        defaultConfig {
            applicationId = "tw.com.deathhit.sunflower_clone"
            minSdk = rootProject.extra["minSdk"] as Int
            targetSdk = compileSdk
            versionCode = rootProject.extra["versionCode"] as Int
            versionName = rootProject.extra["versionName"] as String

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures {
            compose = true
        }

        buildTypes {
            debug {
                /*
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                 */
            }

            release {
                isMinifyEnabled = true
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
    implementation(project(":app:internal:sunflower_clone_kmp_config"))

    //Hilt
    ksp(libs.hilt.compiler)
}