plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "tw.com.deathhit.core.app_ui"
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
    //Appcompat
    api("androidx.appcompat:appcompat:1.7.0")

    //Constraint Layout
    api("androidx.constraintlayout:constraintlayout:2.1.4")

    //Core KTX
    api("androidx.core:core-ktx:1.13.1")

    //Coroutine
    val coroutineVersion = "1.8.1"
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    //Fragment KTX
    api("androidx.fragment:fragment-ktx:1.8.1")

    //Material Design
    api("com.google.android.material:material:1.12.0")

    //Paging
    val pagingVersion = "3.3.0"
    api("androidx.paging:paging-runtime-ktx:$pagingVersion")

    //Recycler View
    api("androidx.recyclerview:recyclerview:1.3.2")

    //View Pager 2
    api("androidx.viewpager2:viewpager2:1.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}