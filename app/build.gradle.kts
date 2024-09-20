plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.nmt.mlkitvision"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nmt.mlkitvision"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        multiDexEnabled = true
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation("androidx.multidex:multidex:2.0.1")
    // Barcode model
    implementation("com.google.mlkit:barcode-scanning:17.3.0")
    // Object detection feature with bundled default classifier
    implementation("com.google.mlkit:object-detection:17.0.2")
    // Object detection feature with custom classifier support
    implementation("com.google.mlkit:object-detection-custom:17.0.2")
    // Face features
    implementation("com.google.mlkit:face-detection:16.1.7")
    // Text features
    implementation("com.google.mlkit:text-recognition:16.0.1")
    implementation ("com.google.mlkit:text-recognition-chinese:16.0.1")
    implementation ("com.google.mlkit:text-recognition-devanagari:16.0.1")
    implementation ("com.google.mlkit:text-recognition-japanese:16.0.1")
    implementation ("com.google.mlkit:text-recognition-korean:16.0.1")
    // Image labeling
    implementation ("com.google.mlkit:image-labeling:17.0.9")
    // Image labeling custom
    implementation ("com.google.mlkit:image-labeling-custom:17.0.3")
    // Pose detection with default models
    implementation ("com.google.mlkit:pose-detection:18.0.0-beta5")
    // Pose detection with accurate models
    implementation ("com.google.mlkit:pose-detection-accurate:18.0.0-beta5")
    // Selfie segmentation
    implementation ("com.google.mlkit:segmentation-selfie:16.0.0-beta6")
    implementation ("com.google.mlkit:camera:16.0.0-beta3")
    // Face Mesh Detection
    implementation ("com.google.mlkit:face-mesh-detection:16.0.0-beta3")
    // Subject Segmentation
    implementation ("com.google.android.gms:play-services-mlkit-subject-segmentation:16.0.0-beta1")

    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.google.guava:guava:31.1-android")
    // ViewModel and LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata:2.8.5")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.8.5")

    // CameraX
    implementation ("androidx.camera:camera-camera2:1.3.4")
    implementation ("androidx.camera:camera-lifecycle:1.3.4")
    implementation ("androidx.camera:camera-view:1.3.4")

    // On Device Machine Learnings
    implementation ("com.google.android.odml:image:1.0.0-beta1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}