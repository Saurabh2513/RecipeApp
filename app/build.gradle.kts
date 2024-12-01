import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    // alias(libs.plugins.kotlin-kapt)
    id("org.jetbrains.kotlin.kapt")

}

android {
    namespace = "com.example.recipeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.recipeapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", "apiKey")
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
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx.v131)
    implementation(libs.androidx.appcompat.v120)
    implementation(libs.androidx.constraintlayout.v201)
    implementation(libs.material.v121)
    testImplementation(libs.junit.v412)
    androidTestImplementation(libs.androidx.junit.v111)
    androidTestImplementation(libs.androidx.espresso.core.v320)

    implementation(libs.androidx.recyclerview)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.legacy.support.v4)

    // Glide image loading
    implementation(libs.glide)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // ViewPager2 page indicator
    implementation(libs.viewpagerdotsindicator)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Loading Indicators
    implementation(libs.android.spinkit)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    kapt(libs.androidx.hilt.compiler)

    // Room Database
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx) // Kotlin Extensions and Coroutines support
    testImplementation(libs.androidx.room.testing)// Test helpers

    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)

    // Photo View
    implementation(libs.photoview)

    // Paging
    implementation(libs.androidx.paging.runtime)

    // Play Core
    //noinspection RiskyLibrary
    implementation (libs.core)
    implementation(libs.core.ktx)

    implementation(libs.hilt.android.v2511)
    kapt(libs.hilt.android.compiler.v2511)

    implementation(libs.dotsindicator)

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt.android.v242)
    kapt(libs.hilt.android.compiler.v242)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.extensions)
}