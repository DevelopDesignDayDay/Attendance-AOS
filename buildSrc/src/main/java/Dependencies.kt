object ApplicationId {
    val id = "com.ddd"
}

object Modules {
    val app = ":app"
    val domain = ":domain"
    val presentation = ":presentation"
    val data = ":data"
    val common = ":common"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val kotlin = "1.3.50"
    val gradle = "3.5.0"
    val compileSdk = 29
    val minSdk = 23
    val targetSdk = 29
    val appCompat = "1.0.2"
    val coreKtx = "1.0.2"
    val materialDesign = "1.1.0-alpha06"
    val constraintLayout = "1.1.3"
    val coroutine = "1.3.0"
    val retrofit = "2.6.1"
    val retrofitGson = "2.4.0"
    val retrofitCoroutines = "0.9.2"
    val gson = "2.8.5"
    val okHttp = "4.1.0"
    val dagger = "2.24"
    val lifecycle = "2.1.0-alpha04"
    val recyclerview = "1.0.0"
    val glide = "4.9.0"

}

object Libraries {
    // Dagger2
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // COROUTINE
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"

    // RETROFIT
    val retrofitCoroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    // GLIDE
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidLibraries {
    // ANDROID
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
}
