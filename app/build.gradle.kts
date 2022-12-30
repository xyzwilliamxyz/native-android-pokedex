plugins {
    id(Dependencies.Plugin.ANDROID_APPLICATION)
    id(Dependencies.Plugin.KOTLIN_ANDROID)
    id(Dependencies.Plugin.KAPT)
    id(Dependencies.Plugin.HILT)
}

android {
    compileSdk = Config.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.example.pokedex"
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.COMPOSE_VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Compose
    val composeBom = platform(Dependencies.COMPOSE_BOM)
    implementation(composeBom)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_FOUNDATION)
    implementation(Dependencies.COMPOSE_UI)
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Dependencies.COMPOSE_UI_TOOLING)
    debugImplementation(Dependencies.TEST_COMPOSE_MANIFEST)

    implementation(Dependencies.ACCOMPANIST_SYSTEMUICONTROLLER)
    implementation(Dependencies.ACCOMPANIST_PAGER)
    implementation(Dependencies.COIL_COMPOSE)
    implementation(Dependencies.COIL_GIF)

    // KTX libraries
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)

    // Hilt
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_COMPILER)
    kapt(Dependencies.HILT_COMPILER_ANDROIDX)
    implementation(Dependencies.HILT_COMPOSE_NAVIGATION)

    // Retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.MOSHI_KOTLIN)
    implementation(Dependencies.OK_HTTP_LOGGING_INTERCEPTOR)

    // Test
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROID_EXT_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO_CORE)
    androidTestImplementation(Dependencies.COMPOSE_JUNIT)
}