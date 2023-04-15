object Dependencies {
    private const val RETROFIT_VERSION = "2.9.0"
    private const val MOSHI_VERSION = "1.13.0"
    private const val HILT_VERSION = "2.42"
    private const val HILT_ANDROIDX_VERSION = "1.0.0"
    private const val ESPRESSO_VERSION = "3.4.0"
    private const val OK_HTTP_VERSION = "4.9.1"
    private const val ACCOMPANIST_VERSION = "0.26.0-alpha"
    private const val COIL_VERSION = "2.1.0"
    const val COMPOSE_VERSION = "1.3.0-beta01"
    const val KOTLIN_VERSION = "1.7.10"
    const val GRADLE_VERSION = "7.4.2"

    const val CORE_KTX = "androidx.core:core-ktx:1.8.0"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:$MOSHI_VERSION"
    const val OK_HTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$OK_HTTP_VERSION"

    const val COMPOSE_BOM = "androidx.compose:compose-bom:2022.10.00"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material"

    const val ACCOMPANIST_SYSTEMUICONTROLLER = "com.google.accompanist:accompanist-systemuicontroller:$ACCOMPANIST_VERSION"
    const val ACCOMPANIST_PAGER = "com.google.accompanist:accompanist-pager:$ACCOMPANIST_VERSION"
    const val COIL_COMPOSE = "io.coil-kt:coil-compose:$COIL_VERSION"
    const val COIL_GIF = "io.coil-kt:coil-gif:$COIL_VERSION"

    const val HILT_ANDROID = "com.google.dagger:hilt-android:$HILT_VERSION"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:$HILT_VERSION"
    const val HILT_COMPILER_ANDROIDX = "androidx.hilt:hilt-compiler:$HILT_ANDROIDX_VERSION"
    const val HILT_COMPOSE_NAVIGATION = "androidx.hilt:hilt-navigation-compose:$HILT_ANDROIDX_VERSION"

    const val JUNIT = "junit:junit:4.13.2"
    const val CORE_KTX_TEST = "androidx.test:core-ktx:1.4.0"
    const val ANDROID_EXT_JUNIT = "androidx.test.ext:junit-ktx:1.1.3"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
    const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:$COMPOSE_VERSION"
    const val TEST_COMPOSE_MANIFEST = "androidx.compose.ui:ui-test-manifest"

    object Classpath {
        const val HILT = "com.google.dagger:hilt-android-gradle-plugin:$HILT_VERSION"
    }

    object Plugin {
        const val KAPT = "kotlin-kapt"
        const val HILT = "dagger.hilt.android.plugin"
        const val ANDROID_APPLICATION = "com.android.application"
        const val ANDROID_LIBRARY = "com.android.library"
        const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    }
}
