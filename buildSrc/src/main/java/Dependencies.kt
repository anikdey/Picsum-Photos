/**
 * To define plugins
 */
object BuildPlugins {

    val androidBuildTools by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlinPlugins by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    val navigationSafeArg by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}" }

}

object KotlinDependencies {

    val kotlinStd by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }

}

object Modules {

    const val DOMAIN = ":domain"
    const val DATA = ":data"
    const val COMMON = ":common"

}

object AndroidXSupportDependencies {

    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val lifecycle by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1" }
    val activityCompose by lazy { "androidx.activity:activity-compose:1.7.2" }
    val splashscreen by lazy { "androidx.core:core-splashscreen:1.0.1" }

    val composeUI by lazy { "androidx.activity:activity-compose:${Versions.composeUI}" }
    val composeToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.composeUI}" }
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.composeUI}" }


    val pagingCompose by lazy { "androidx.paging:paging-compose:3.2.0-rc01" }
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:2.6.0" }

}

object MaterialDesignDependencies {
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
}

object Libraries {

    // hilt
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }

    // retrofit
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
    val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}" }

    // moshi
    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" }
    val moshiKotlinCodegen by lazy { "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}" }

    val coilCompose by lazy { "io.coil-kt:coil-compose:2.2.2" }
    val gson by lazy { "com.google.code.gson:gson:2.10.1" }

}

object TestingLibraries {

    val junit by lazy { "junit:junit:4.13.2" }
    val extJunit by lazy { "androidx.test.ext:junit:1.1.5" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:3.5.1" }

    val composeUITest by lazy { "androidx.compose.ui:ui-test-junit4:1.4.3" }
    val composeUITestManifest by lazy { "androidx.compose.ui:ui-test-manifest:1.4.3" }

    val mockwebserver by lazy { "com.squareup.okhttp3:mockwebserver:4.10.0" }
    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4" }
    val truth by lazy { "com.google.truth:truth:1.1.3" }
    val mockitoCore by lazy { "org.mockito:mockito-core:4.0.0" }
    val mockitoInline by lazy { "org.mockito:mockito-inline:4.0.0" }
    val archCoreTesting by lazy { "androidx.arch.core:core-testing:2.2.0" }

}


