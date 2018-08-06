object Plugins {
    val kotlinAndroid = "kotlin-android"
    val kotlinAndroidExt = "kotlin-android-extensions"
}

object BuildDetails {
    val compileSdk = 28
    val minSdk = 19
    val targetSdk = 28
    val versionCode = 1
    val versionName = "1.0"

    val libraryPackageName = "in.arunkumarsampath.transitionx"
    val librarySample = libraryPackageName + ".sample"
}


object Versions {
    val agp = "3.2.0-beta05"
    val supportLib = "27.1.1"
    val constraintLayout = "1.1.2"
    val jUnit = "4.12"
    val testRunner = "1.0.2"
    val espresso = "3.0.2"
    val kotlin = "1.2.60"
    val androidKtx = "0.3"
}

object Dependencies {
    // AGP
    val agp = "com.android.tools.build:gradle:${Versions.agp}"
    // Kotlin
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // Support
    val appCompat = "com.android.support:appcompat-v7:${Versions.supportLib}"
    val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"
    val supportDesign = "com.android.support:design:${Versions.supportLib}"

    // KTX
    val androidKtx = "androidx.core:core-ktx:${Versions.androidKtx}"

    // Test
    val jUnit = "junit:junit:${Versions.jUnit}"
    val androidJUnitRunner = "android.support.test.runner.AndroidJUnitRunner"
    val testRunner = "com.android.support.test:runner:${Versions.testRunner}"
    val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
}