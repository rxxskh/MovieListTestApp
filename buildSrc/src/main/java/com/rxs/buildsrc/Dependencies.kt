package com.rxs.buildsrc

object Versions {
    const val coroutinesCore = "1.7.3"
    const val androidXCore = "1.12.0"
    const val appcompat = "1.6.1"
    const val material = "1.11.0"
    const val constraintLayout = "2.1.4"
    const val activityCompose = "1.8.2"
    const val retrofit = "2.9.0"
    const val daggerHilt = "2.50"
    const val picasso = "2.8"
    const val nav = "2.7.7"
    const val junit = "4.13.2"
    const val testExtJunit = "1.1.5"
    const val espresso = "3.5.1"
}

object Dependencies {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"

    const val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

    const val junit = "junit:junit:${Versions.junit}"
    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val javaxInject = "javax.inject:javax.inject:1"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerHiltKapt = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
}