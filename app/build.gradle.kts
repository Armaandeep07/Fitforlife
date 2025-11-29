plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.fitforlife"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.fitforlife"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        // Add this for better compatibility
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        viewBinding = true
    }

    // Add build types for better configuration
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
//    composeOptions {
//            kotlinCompilerExtensionVersion = "1.5.4"
//        }
    }

    // Add Kotlin options for better compatibility
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
//    // Compose BOM (Bill of Materials) - Recommended
//    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
//
//    // Required Compose dependencies
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3") // This provides MaterialTheme
//    implementation("androidx.activity:activity-compose:1.8.0")

    // If you need Android Studio Preview support
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")

    // Firebase BoM (updated to latest version)
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // Firebase dependencies
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // AndroidX
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Optional: Add lifecycle components for better architecture
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation(libs.androidx.activity)
    implementation(libs.androidx.material3)
}
