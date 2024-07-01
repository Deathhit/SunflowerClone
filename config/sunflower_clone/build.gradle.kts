plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
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
    val coroutineVersion = "1.8.1"
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")

    //Gson
    implementation("com.google.code.gson:gson:2.11.0")

    //Hilt
    val hiltVersion = "2.51.1"
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")

    //Hilt-Work Manager
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    api("androidx.hilt:hilt-work:1.2.0")

    //Work Manager
    val workVersion = ("2.9.0")
    implementation("androidx.work:work-runtime-ktx:$workVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

hilt {
    enableAggregatingTask = true
}

kapt {
    //Hilt: Allow references to generated code
    correctErrorTypes = true
}