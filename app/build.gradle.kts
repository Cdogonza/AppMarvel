plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.marvel_app_final"
    compileSdk = 34
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.example.marvel_app_final"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation ("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation("com.google.android.gms:play-services-auth:20.7.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.work:work-runtime-ktx:2.8.1")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.20")
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Navigation Components
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Koin
    implementation ("io.insert-koin:koin-core:3.5.0")
    testImplementation ("io.insert-koin:koin-test:3.5.0")
    implementation ("io.insert-koin:koin-android:3.5.0")

    // Java Compatibility
    implementation ("io.insert-koin:koin-android-compat:3.1.5")

    // Navigation Graph
    implementation ("io.insert-koin:koin-androidx-navigation:3.1.6")


    // Koin for Ktor
    implementation ("io.insert-koin:koin-ktor:3.1.4")

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Paging3
    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
    implementation("com.github.bumptech.glide:okhttp3-integration:4.16.0")
    implementation("com.github.bumptech.glide:annotations:4.16.0")

    //implementar picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    implementation("com.google.android.material:material:1.10.0")
    implementation("com.google.code.gson:gson:2.9.0")

}