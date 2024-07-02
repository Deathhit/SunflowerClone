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
    api(libs.androidx.appcompat)

    //Constraint Layout
    api(libs.constraintlayout)

    //Core KTX
    api(libs.androidx.core.ktx)

    //Coroutine
    implementation(libs.jetbrains.koltin.coroutine)

    //Fragment KTX
    api(libs.androidx.fragment.ktx)

    //Material Design
    api(libs.material)

    //Paging
    api(libs.paging.runtime)

    //Recycler View
    api(libs.recyclerview)

    //View Pager 2
    api(libs.viewpager2)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}