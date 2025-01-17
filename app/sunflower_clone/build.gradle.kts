plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.parcelize)
}

android {
    namespace = "tw.com.deathhit.app.sunflower_clone"
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
        viewBinding = true
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

    kotlinOptions {
        jvmTarget = rootProject.extra["kotlinJvmTarget"] as String
    }
}

dependencies {
    implementation(project(":app:internal:sunflower_clone_config"))
    implementation(project(":core:sunflower_clone_ui"))
    implementation(project(":domain"))
    implementation(project(":feature:gallery"))
    implementation(project(":feature:navigation"))
    implementation(project(":feature:plant_details"))

    //Androidx KTX
    implementation(libs.androidx.core.ktx)

    //Coroutine
    implementation(libs.jetbrains.koltin.coroutine)
    androidTestImplementation(libs.jetbrains.koltin.coroutine.test)

    //Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

