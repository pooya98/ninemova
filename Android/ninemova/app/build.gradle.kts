import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.ninemova"
    compileSdk = 34

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())
    val authorizationName = properties["AUTHORIZATION_NAME"] ?: ""
    val authorizationValue = properties["AUTHORIZATION_VALUE"] ?: ""
    val contentTypeName = properties["CONTENT_TYPE_NAME"] ?: ""
    val contentTypeValue = properties["CONTENT_TYPE_VALUE"] ?: ""
    val tmdbBaseUrl = properties["TMDB_BASE_URL"] ?: ""
    val tmdbAccessToken = properties["TMDB_ACCESS_TOKEN"]
    val youtubeApiKey = properties["YOUTUBE_API_KEY"] ?: ""
    val youtubeBaseUrl = properties["YOUTUBE_BASE_URL"]
    val openAIApiKey = properties["OPENAI_API_KEY"] ?: ""
    val openAIBaseUrl = properties["OPENAI_BASE_URL"] ?: ""
    val baseServerUrl = properties["NINEMOVA_SERVER_URL"] ?: ""

    defaultConfig {
        applicationId = "com.ninemova"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "AUTHORIZATION_NAME", "$authorizationName")
        buildConfigField("String", "AUTHORIZATION_VALUE", "$authorizationValue")
        buildConfigField("String", "CONTENT_TYPE_NAME", "$contentTypeName")
        buildConfigField("String", "CONTENT_TYPE_VALUE", "$contentTypeValue")
        buildConfigField("String", "TMDB_BASE_URL", "$tmdbBaseUrl")
        buildConfigField("String", "TMDB_ACCESS_TOKEN", "$tmdbAccessToken")
        buildConfigField("String", "YOUTUBE_API_KEY", "$youtubeApiKey")
        buildConfigField("String", "YOUTUBE_BASE_URL", "$youtubeBaseUrl")
        buildConfigField("String", "OPENAI_API_KEY", "$openAIApiKey")
        buildConfigField("String", "OPENAI_BASE_URL", "$openAIBaseUrl")
        buildConfigField("String", "BASE_SERVER_URL", "$baseServerUrl")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // retrofit,
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // okHttpClient
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // gilde
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.1.0")

    // FlowLayout
    implementation("com.google.android.flexbox:flexbox:3.0.0")

    // viewpager dot indicator
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.tbuonomo:dotsindicator:5.0")

    // pie chart
    implementation("com.github.blackfizz:eazegraph:1.2.5l@aar")
    implementation("com.nineoldandroids:library:2.4.0")

    // Youtube Api
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // rating bar
    implementation("com.github.ome450901:SimpleRatingBar:1.5.1")

    // FireBase
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
}
