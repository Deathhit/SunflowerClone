import org.gradle.internal.extensions.core.extra

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "tw.com.deathhit.core.sunflower_clone_ui"
    compileSdk = rootProject.extra["compileSdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdk"] as Int

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
        val javaVersion = rootProject.extra["javaVersion"] as JavaVersion

        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = rootProject.extra["kotlinJvmTarget"] as String
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