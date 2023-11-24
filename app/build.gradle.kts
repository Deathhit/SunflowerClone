plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "tw.com.deathhit.sunflower_clone"
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
    implementation(project(":core:app_database"))
    implementation(project(":core:app_ui"))
    implementation(project(":core:unsplash_api"))
    implementation(project(":data:garden_planting"))
    implementation(project(":data:photo"))
    implementation(project(":data:plant"))
    implementation(project(":domain"))
    implementation(project(":feature:gallery"))
    implementation(project(":feature:garden_planting_list"))
    implementation(project(":feature:navigation"))
    implementation(project(":feature:plant_details"))
    implementation(project(":feature:plant_list"))

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    //Hilt
    val hiltVersion = "2.48.1"
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")

    //Hilt-Work Manager
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-work:1.1.0")

    //Work Manager
    val workVersion = ("2.8.1")
    implementation("androidx.work:work-runtime-ktx:$workVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

hilt {
    enableAggregatingTask = true
}

kapt {
    //Hilt: Allow references to generated code
    correctErrorTypes = true
}