plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "com.android.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(Modules.DATA))
    implementation(project(Modules.COMMON))
    //hilt
    implementation (Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    // Local Unit Tests
    testImplementation (TestingLibraries.junit)
    testImplementation (TestingLibraries.coroutinesTest)
    testImplementation (TestingLibraries.truth)
    testImplementation (TestingLibraries.mockitoCore)
    testImplementation (TestingLibraries.mockitoInline)
    testImplementation (TestingLibraries.mockwebserver)
    testImplementation (TestingLibraries.archCoreTesting)

    testImplementation (Libraries.moshiConverter)
    testImplementation (Libraries.moshiKotlin)
}