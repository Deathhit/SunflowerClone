plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
}

android {
    namespace = "tw.com.deathhit.config.sunflower_clone"
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

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core:app_database"))
    implementation(project(":core:unsplash_api"))
    implementation(project(":data:garden_planting"))
    implementation(project(":data:photo"))
    implementation(project(":data:plant"))
    implementation(project(":domain"))

    //Coroutine-Test
    androidTestImplementation(libs.jetbrains.koltin.coroutine.test)

    //Gson
    implementation(libs.gson)

    //Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.hilt)

    //Hilt-Work Manager
    kapt(libs.hilt.work.compiler)
    api(libs.hilt.work)

    //Work Manager
    implementation(libs.work)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}