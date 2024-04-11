plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    val composeBom = platform("androidx.compose:compose-bom:2024.03.00")
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

    //Glide
    api("com.github.bumptech.glide:compose:1.0.0-beta01")

    //Hilt-Navigation-Compose
    api("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Paging
    api("androidx.paging:paging-compose:3.3.0-beta01")

    //View Model-Compose
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}