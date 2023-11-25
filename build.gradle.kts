// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.1.3" apply false
    id ("org.jetbrains.kotlin.android.extensions") version "1.9.0" apply false
    id ("com.google.dagger.hilt.android") version "2.42" apply false
    id ("org.jetbrains.kotlin.kapt") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
}

buildscript {


    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6")

    }
}
