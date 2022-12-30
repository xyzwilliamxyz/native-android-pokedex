buildscript {
    dependencies {
        classpath(Dependencies.Classpath.HILT)
    }
}

plugins {
    id(Dependencies.Plugin.ANDROID_LIBRARY) version Dependencies.GRADLE_VERSION apply false
    id(Dependencies.Plugin.KOTLIN_ANDROID) version Dependencies.KOTLIN_VERSION apply false
}