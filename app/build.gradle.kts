plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kapt.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
}

android {
    compileSdk = libs.versions.configCompileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.example.pokedex"
        minSdk = libs.versions.configMinSdkVersion.get().toInt()
        targetSdk = libs.versions.configTargetSdkVersion.get().toInt()
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_API_URL", "\"https://pokeapi.co/api/v2/\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.example.pokedex"
}

dependencies {
    // Compose
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.foundation)
    debugImplementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.tooling)
    implementation(libs.compose.navigation)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.pager)
    implementation(libs.coil)
    implementation(libs.coil.gif)

    // KTX libraries
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.logging.interceptor)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ktx)
    androidTestImplementation(libs.espresso.core)
}