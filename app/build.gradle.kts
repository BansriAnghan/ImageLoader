plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.bansri.imageloader"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bansri.imageloader"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "UNSPLASH_API_KEY", "\"https://api.unsplash.com\"")
            buildConfigField("String", "ACCESS_KEY", "\"Ai6fBY9as11ptGy9tB3PiqQMfedE9T88YY6LknDS4Vc\"")
            buildConfigField("String", "SECRET_KEY", "\"T1UIc-19qwhLRES6LBU2QNIpP7st4j37AZhI1BgodXM\"")

        }
        release {
            buildConfigField("String", "UNSPLASH_API_KEY", "\"https://api.unsplash.com\"")
            buildConfigField("String", "ACCESS_KEY", "\"Ai6fBY9as11ptGy9tB3PiqQMfedE9T88YY6LknDS4Vc\"")
            buildConfigField("String", "SECRET_KEY", "\"T1UIc-19qwhLRES6LBU2QNIpP7st4j37AZhI1BgodXM\"")

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
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // retrofit and okhttp
    implementation("com.squareup.retrofit2:retrofit:2.4.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.4.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.4.0")

    // paging
    implementation("androidx.paging:paging-runtime:2.1.0")
    implementation("androidx.paging:paging-rxjava2:2.1.0")

    // misc
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.jakewharton.rxbinding2:rxbinding:2.1.1")
    implementation("com.squareup.okhttp3:logging-interceptor:3.11.0")

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.2.0")

}