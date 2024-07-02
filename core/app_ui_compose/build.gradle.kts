plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "tw.com.deathhit.core.app_ui_compose"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    api(project(":core:app_ui"))

    //Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    api(composeBom)
    androidTestApi(composeBom)

    //Compose-Material 3
    api("androidx.compose.material3:material3")

    //Compose-Preview
    api("androidx.compose.ui:ui-tooling-preview")
    debugApi("androidx.compose.ui:ui-tooling")

    //Compose-View Binding
    api("androidx.compose.ui:ui-viewbinding")

    //Constraint Layout
    api("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //Glide-Compose
    api(libs.glide.compose)

    //Hilt-Navigation-Compose
    api("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Paging
    api(libs.paging.compose)

    //View Model-Compose
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}