plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "com.android.picsumphotos"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationID
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "com.anik.fooddelivery.CustomTestRunner"
    }

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        named("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        named("debug") {
            isMinifyEnabled = false
            isDebuggable = true
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }

    packaging {
        resources.excludes.add("META-INF/*")
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("**/attach_hotspot_windows.dll")
    }

    testOptions {
        animationsDisabled = true
        unitTests.isReturnDefaultValues = true
    }

}

dependencies {
    implementation(project(Modules.COMMON))
    implementation(project(Modules.DOMAIN))

    implementation (AndroidXSupportDependencies.coreKtx)
    implementation (AndroidXSupportDependencies.lifecycle)
    implementation (AndroidXSupportDependencies.activityCompose)
    implementation (AndroidXSupportDependencies.splashscreen)

    implementation (AndroidXSupportDependencies.composeUI)
    implementation (AndroidXSupportDependencies.composeToolingPreview)
    implementation (AndroidXSupportDependencies.composeMaterial)
    implementation (AndroidXSupportDependencies.pagingCompose)
    implementation (AndroidXSupportDependencies.navigationCompose)


    //hilt
    implementation (Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    implementation(Libraries.coilCompose)
    implementation(Libraries.gson)

    testImplementation (TestingLibraries.junit)
    androidTestImplementation (TestingLibraries.extJunit)
    androidTestImplementation (TestingLibraries.espressoCore)
    androidTestImplementation(TestingLibraries.composeUITest)
    debugImplementation(TestingLibraries.composeUITestManifest)

    testImplementation (TestingLibraries.coroutinesTest)
    testImplementation (TestingLibraries.archCoreTesting)
    testImplementation (Libraries.moshiConverter)
    testImplementation (Libraries.moshiKotlin)
    testImplementation (TestingLibraries.mockitoCore)
    testImplementation (TestingLibraries.mockitoInline)
    testImplementation (TestingLibraries.truth)

//    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
}