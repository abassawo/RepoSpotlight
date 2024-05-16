plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version("2.0.1")
}

android {
    namespace = "com.lindenlabs.repospotlight"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lindenlabs.repospotlight"
        minSdk = 24
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

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.paging:paging-compose:1.0.0-alpha12")
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("androidx.browser:browser:1.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    val timberVersion = "5.0.1"
    implementation("com.jakewharton.timber:timber:$timberVersion")
    val retrofitVersion = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.google.dagger:hilt-android:2.48")
    val hiltNavCompose =  "1.0.0"
    implementation(
        "androidx.hilt:hilt-navigation-compose:${hiltNavCompose}")

    kapt("com.google.dagger:hilt-compiler:2.48")

    val navCompose = "2.6.0-alpha03"
    implementation("androidx.navigation:navigation-compose:${navCompose}")

    val coroutinesTestVersion = "1.6.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${coroutinesTestVersion}\",")

    val leakCanaryVersion = "2.10"
    debugImplementation("com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion")

    testImplementation(libs.junit)

    val mockitoVersion = "4.4.0"
    testImplementation("org.mockito:mockito-core:${mockitoVersion}",)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}