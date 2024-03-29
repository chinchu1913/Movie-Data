object Versions {
    //Core
    const val kotlin = "1.6.10"
    const val compose = "1.1.1"
    const val activityCompose = "1.3.1"
    const val dagger = "2.38.1"
    const val navigation = "2.3.2"
    const val hilt = "2.40.5"
    const val hiltCompiler = "1.0.0"
    const val hiltViewModel = "1.0.0-alpha03"
    const val hiltNavigation = "1.0.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.0"
    const val moshi = "1.11.0"
    const val converterMoshi = "2.9.0"
    const val coroutines = "1.4.2"
    const val glide = "4.12.0"
    const val timber = "4.7.1"
    const val lifecycle = "2.2.0"
    const val androidx_core = "1.3.2"
    const val legacy = "1.0.0"
    const val retrofitCoroutines = "0.9.2"
    const val multidex = "2.0.1"

    //UI
    const val constraintlayout = "2.0.4"
    const val appcompat = "1.2.0"
    const val material = "1.3.0"
    const val swipelayout = "1.1.0"
    const val coil = "2.0.0"
    const val composeNavigation = "1.5.5-beta"

    //Test
    const val assertj = "3.6.2"
    const val coreTesting = "2.1.0"
    const val mockito = "3.5.13"
    const val mockitoKotlin = "2.2.0"
    const val coroutinesTest = "1.4.2"
    const val jUnit = "4.12"
    const val espresso = "3.3.0"
    const val androidxJunit = "1.1.2"
    const val googleTruth = "1.0.1"
    const val swipeRefreshCompose = "0.23.1"
}

object AppMetaData {
    const val id = "com.zattoo.movies"
    const val compileSdkVersion = 31
    const val targetSdkVersion = 29
    const val minSdkVersion = 21
    const val buildToolsVersion = "30.0.3"
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Deps {
    //Core
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacy}"

    //UI
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val swiperefresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipelayout}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val swipeRefreshCompose =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.swipeRefreshCompose}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"


    //Dagger
    const val hiltAndroid =  "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltViewModel =  "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val hiltCompilerAndroid =  "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltCompiler =  "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
    const val hiltNavigation =  "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"


    //compose navigation
    const val composeNavigationDestination = "io.github.raamcosta.compose-destinations:core:${Versions.composeNavigation}"
    const val composeNavigationKsp = "io.github.raamcosta.compose-destinations:ksp:${Versions.composeNavigation}"



    //Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitCoroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshi}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // Moshi
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    //Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //Lifecycle
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    //Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    //Multidex
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    //Test
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val mockitoCore = "org.mockito:mockito-core:3.0.0${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val assertJ = "org.assertj:assertj-core:${Versions.assertj}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espressoIdlingResource =
        "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
    const val googleTruth = "com.google.truth:truth:${Versions.googleTruth}"
}