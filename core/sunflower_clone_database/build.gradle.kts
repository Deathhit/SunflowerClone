plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "tw.com.deathhit.core.sunflower_clone_database"
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

    kotlinOptions {
        jvmTarget = rootProject.extra["kotlinJvmTarget"] as String
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    //Coroutine
    implementation(libs.jetbrains.koltin.coroutine)
    androidTestImplementation(libs.jetbrains.koltin.coroutine.test)

    //Paging
    implementation(libs.paging.runtime)
    androidTestImplementation(libs.paging.test)

    //Room
    ksp(libs.room.compiler)
    api(libs.room.ktx)
    api(libs.room.paging)
    api(libs.room.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}