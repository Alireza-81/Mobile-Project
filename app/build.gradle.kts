plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.airline"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.airline"
        minSdk = 30
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("com.google.code.gson:gson:2.8.9")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.firebase.crashlytics.buildtools)
<<<<<<< HEAD
    implementation(libs.constraintlayout)
=======
    implementation(libs.car.ui.lib)
>>>>>>> e79767630b8c58e09b6d51197f1bc27169a9aad4
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}