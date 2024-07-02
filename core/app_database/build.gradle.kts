plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
}

android {
    namespace = "tw.com.deathhit.core.app_database"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    //Coroutine
    val coroutineVersion = "1.8.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    //Coroutine-Test
    androidTestImplementation(libs.jetbrains.koltin.coroutine.test)

    //Paging
    val pagingVersion = "3.3.0"
    api("androidx.paging:paging-runtime-ktx:$pagingVersion")

    //Paging-Test
    androidTestImplementation("androidx.paging:paging-testing:$pagingVersion")

    //Room
    val roomVersion = "2.6.1"
    kapt("androidx.room:room-compiler:$roomVersion")
    api("androidx.room:room-ktx:$roomVersion")
    api("androidx.room:room-paging:$roomVersion")
    api("androidx.room:room-runtime:$roomVersion")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}