import java.io.File
import java.io.FileInputStream
import java.util.*

object Config {
    const val applicationID = "com.android.picsumphotos"
    const val compileSdkVersion = 33
    const val minSdkVersion = 24
    const val targetSdkVersion = 33
    const val versionCode = 1
    const val versionName = "1.0"

    //val androidBuildTools by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
}

object KeyHelper {

    const val KEY_STORE_FILE = "storeFile"
    const val KEY_STORE_PASS = "storePassword"
    const val KEY_ALIAS = "keyAlias"
    const val KEY_PASS = "keyPassword"

    private val properties by lazy {
        Properties().apply { load(FileInputStream(File("release.properties"))) }
    }

    fun getValue(key: String): String {
        return properties.getProperty(key)
    }
}