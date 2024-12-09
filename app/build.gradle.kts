    plugins {
        id ("com.android.application")
        id ("kotlin-android")
        id ("kotlin-kapt") // Required for annotation processing
        id ("dagger.hilt.android.plugin")
    }

android {
    namespace = "com.makaota.townsquare"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.makaota.townsquare"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

    kapt {
        correctErrorTypes = true  // Helps resolve annotation processing issues
        useBuildCache = false  // Optional: disable build cache for KAPT
    }

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))


    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation(libs.firebase.analytics)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.material3)

    //splash api library
    implementation(libs.androidx.core.splashscreen)


    //DataStore Preference Library
    implementation(libs.androidx.datastore.preferences)

    // Hilt Libraries
    implementation(libs.androidx.hilt.navigation.compose) // For navigation-compose


    implementation (libs.hilt.android)  // Hilt dependency
    kapt (libs.hilt.android.compiler.v249)  // Hilt compiler for annotation processing
   implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.0")  // Kotlin stdlib

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")


    // lib phonenumber dependency
    implementation("com.googlecode.libphonenumber:libphonenumber:8.12.52")


}

