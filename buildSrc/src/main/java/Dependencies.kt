object Plugins {
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExt = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val navigationPlugin = "androidx.navigation.safeargs"
}

object BuildDetails {
    const val compileSdk = 28
    const val minSdk = 19
    const val targetSdk = 28
    const val versionCode = 1
    const val versionName = "1.0"
    const val libraryPackageName = "in.arunkumarsampath.transitionx"
    val librarySample = "$libraryPackageName.sample"
}

object Versions {
    const val agp = "3.3.0-alpha09"
    const val supportLib = "28.0.0-alpha3"
    const val constraintLayout = "1.1.2"
    const val kotlin = "1.2.60"
    const val androidKtx = "0.3"
    const val archNavigation = "1.0.0-alpha01"
    const val flexBox = "1.0.0"
    const val timber = "4.7.1"
    const val glide = "4.8.0"
    const val adapterDelegates = "3.0.1"

    // Tests
    const val jUnit = "4.12"
    const val testRunner = "1.0.2"
    const val espresso = "3.0.2"
    const val roboElectric = "3.8"

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
    val recyclerView = "com.android.support:recyclerview-v7:${Versions.supportLib}"

    // Flexbox layout
    val flexBox = "com.google.android:flexbox:${Versions.flexBox}"

    // Timber
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // KTX
    val androidKtx = "androidx.core:core-ktx:${Versions.androidKtx}"

    // Jetpack navigation
    val navigationFragment = "android.arch.navigation:navigation-fragment:${Versions.archNavigation}"
    val navigationFragmentKtx = "android.arch.navigation:navigation-fragment-ktx:${Versions.archNavigation}"
    val navigationUi = "android.arch.navigation:navigation-ui:${Versions.archNavigation}"
    val navigationUiKtx = "android.arch.navigation:navigation-ui-ktx:${Versions.archNavigation}"
    val navigationPlugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.archNavigation}"

    // Adapter Delegates
    val adapterDelegates = "com.hannesdorfmann:adapterdelegates3:${Versions.adapterDelegates}"

    // Image Loading
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Test
    val jUnit = "junit:junit:${Versions.jUnit}"
    val androidJUnitRunner = "android.support.test.runner.AndroidJUnitRunner"
    val testRunner = "com.android.support.test:runner:${Versions.testRunner}"
    val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    val robotElectric = "org.robolectric:robolectric:${Versions.roboElectric}"
}