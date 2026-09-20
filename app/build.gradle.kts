plugins {
    id("com.android.application")
}

android {
    namespace = "com.hotwheel.selfdrive"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hotwheel.selfdrive"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
             
        isMinifyEnabled = false
    }
