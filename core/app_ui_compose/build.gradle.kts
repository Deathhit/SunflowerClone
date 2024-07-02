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
    api(libs.constraintlayout.compose)

    //Glide-Compose
    api(libs.glide.compose)

    //Hilt-Navigation-Compose
    api(libs.hilt.navigation.compose)

    //Paging
    api(libs.paging.compose)

    //View Model-Compose
    api(libs.viewmodel.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}