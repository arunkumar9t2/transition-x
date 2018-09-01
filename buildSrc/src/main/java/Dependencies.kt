object Plugins {
    val kotlinAndroid = "kotlin-android"
    val kotlinAndroidExt = "kotlin-android-extensions"
    val navigationPlugin = "androidx.navigation.safeargs"
}

object BuildDetails {
    val compileSdk = 28
    val minSdk = 19
    val targetSdk = 28
    val versionCode = 1
    val versionName = "1.0"

    val libraryPackageName = "in.arunkumarsampath.transitionx"
    val librarySample = "$libraryPackageName.sample"
}

object Versions {
    val agp = "3.3.0-alpha08"
    val supportLib = "28.0.0-alpha3"
    val constraintLayout = "1.1.2"
    val jUnit = "4.12"
    val testRunner = "1.0.2"
    val espresso = "3.0.2"
    val kotlin = "1.2.60"
    val androidKtx = "0.3"
    val roboElectric = "3.8"
    val mdc = "1.0.0-beta01"
    val archNavigation = "1.0.0-alpha01"
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

    // Jetpack navigation
    val navigationFragment = "android.arch.navigation:navigation-fragment:${Versions.archNavigation}"
    val navigationFragmentKtx = "android.arch.navigation:navigation-fragment-ktx:${Versions.archNavigation}"
    val navigationUi = "android.arch.navigation:navigation-ui:${Versions.archNavigation}"
    val navigationUiKtx = "android.arch.navigation:navigation-ui-ktx:${Versions.archNavigation}"
    val navigationPlugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.archNavigation}"

    // Test
    val jUnit = "junit:junit:${Versions.jUnit}"
    val androidJUnitRunner = "android.support.test.runner.AndroidJUnitRunner"
    val testRunner = "com.android.support.test:runner:${Versions.testRunner}"
    val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    val robotElectric = "org.robolectric:robolectric:${Versions.roboElectric}"
}