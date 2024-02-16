plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "tw.com.deathhit.sunflower_clone.navigation"
    compileSdk = 34

    defaultConfig {
        applicationId = "tw.com.deathhit.sunflower_clone"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":config:sunflower_clone"))
    implementation(project(":core:app_ui"))
    implementation(project(":domain"))
    implementation(project(":feature:gallery"))
    implementation(project(":feature:navigation"))
    implementation(project(":feature:plant_details"))

    //Coroutine-Test
    val coroutineVersion = "1.7.3"
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")

    //Hilt
    val hiltVersion = "2.50"
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")

    //Navigation
    val navVersion = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}