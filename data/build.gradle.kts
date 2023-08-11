plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "com.android.data"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            buildConfigField("String", "PIC_SUM_BASE_URL", findProperty("PIC_SUM_BASE_URL") as String)
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
            buildConfigField("String", "PIC_SUM_BASE_URL", findProperty("PIC_SUM_BASE_URL") as String)
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

    implementation(project(Modules.COMMON))
    
    //hilt
    implementation (Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    //retrofit
    implementation (Libraries.retrofit)
    implementation (Libraries.moshiConverter)
    implementation (Libraries.moshiKotlin)

    // Local Unit Tests
    testImplementation (TestingLibraries.junit)
    testImplementation (TestingLibraries.coroutinesTest)
    testImplementation (TestingLibraries.truth)
    testImplementation (TestingLibraries.mockitoCore)
    testImplementation (TestingLibraries.mockitoInline)
    testImplementation (TestingLibraries.mockwebserver)
    testImplementation (TestingLibraries.archCoreTesting)

}

