buildscript {
    dependencies {
        classpath(libs.hilt.android.classpath)
    }
}

plugins {
    id(libs.plugins.android.library.get().pluginId) version libs.versions.gradlePlugin apply false
    id(libs.plugins.kotlin.android.get().pluginId) version libs.versions.kotlin apply false
}