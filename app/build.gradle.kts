plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id ("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs")
    id ("kotlin-kapt")

}

android {
    namespace = "com.example.test"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.test"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //life cycle
    implementation (libs.androidx.lifecycle.common)
    implementation (libs.androidx.fragment.ktx)

    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    // Navigation
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.runtime.ktx)
    implementation (libs.androidx.navigation.ui.ktx)
    // Dagger Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    // Glide
    implementation (libs.github.glide)
    annotationProcessor (libs.compiler.v4151)
    // Retrofit + GSON
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    // Paging 3 Dependency
    implementation (libs.androidx.paging.runtime)
    // If you're using Coroutines
    implementation (libs.kotlinx.coroutines.android)

    //shimmer
    implementation (libs.shimmer)
}