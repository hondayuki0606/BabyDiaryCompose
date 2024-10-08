import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
//    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}
//kapt {
//    correctErrorTypes true
//}
android {

    namespace = "com.example.babydiarycompose"
    compileSdk = 34
    buildToolsVersion = "34.0.0"
    defaultConfig {
        applicationId = "com.example.babydiarycompose"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.example.babydiarycompose.CustomTestRunner"
        // Consult the README on instructions for setting up Unsplash API key
        buildConfigField("String", "UNSPLASH_ACCESS_KEY", "\"" + getUnsplashAccess() + "\"")

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://localhost:3000/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://localhost:3000/\"")
        }
    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//    kotlin {
//        jvmToolchain(8)
//    }
//    hilt {
//        enableTransformForLocalTests = true
//    }
//    testOptions {
//        unitTests.includeAndroidResources = true
//    }
    kotlinOptions {
        jvmTarget = "17"
//        freeCompilerArgs = ["-Xcontext-receivers"]
    }
//    kotlinOptions {
//
//    }
//    testOptions {
//        unitTests.includeAndroidResources = true
//    }
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
            )
        )
    }
}

dependencies {

    implementation("com.google.ar:core:1.42.0")
    // room
    val roomVersion = "2.5.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")


    // Hilt Navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // Use Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    ksp("com.google.dagger:hilt-android-compiler:2.48")
    annotationProcessor("androidx.hilt:hilt-compiler:1.2.0")

    testImplementation("com.google.dagger:hilt-android-testing:2.48")
    kspTest("com.google.dagger:hilt-android-compiler:2.48")
    testAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.48")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.48")

    // constraintlayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // navigationを使用
    implementation("androidx.compose.material:material:1.6.3")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // foundationを使用
    implementation("androidx.compose.foundation:foundation:1.6.3")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // compose
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    // bomを使用しているのでcomposeのバージョンは指定しない
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation("org.robolectric:robolectric:4.10.3")
    androidTestImplementation("io.mockk:mockk:1.13.5")
    androidTestImplementation("io.mockk:mockk-android:1.13.5")
    androidTestImplementation("io.mockk:mockk-agent:1.13.5")
    implementation("com.google.accompanist:accompanist-webview:0.28.0")
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    /**
     * Swagger Annotations
     * Swagger Models
     * Jakarta Annotations API
     */
    compileOnly("io.swagger.core.v3:swagger-annotations:2.2.4")
    compileOnly("io.swagger.core.v3:swagger-models:2.2.4")
    compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    // Retrofit
    val retrofit2Version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit2Version")
    // Retrofit with Scalar Converter
    implementation("com.squareup.retrofit2:converter-scalars:$retrofit2Version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.activity:activity-ktx:1.9.2")
}

fun getUnsplashAccess(): String? {
    return project.findProperty("unsplash_access_key") as? String
}