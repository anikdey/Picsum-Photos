package com.android.domain.util

import com.android.data.dto.PhotoResponseDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.ArgumentCaptor
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

object TestUtil {
    fun immediateExecutorService(): ExecutorService {
        return object : AbstractExecutorService() {
            override fun execute(command: Runnable) {
                command.run()
            }

            override fun shutdown() {}

            override fun shutdownNow(): MutableList<Runnable>? = null

            override fun isShutdown(): Boolean = false

            override fun isTerminated(): Boolean = false

            override fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean {
                return false
            }
        }
    }

    @Throws(IOException::class)
    fun mockResponse(fileName: String): MockResponse {
        return MockResponse().setChunkedBody(readFileToString(TestUtil::class.java, "/$fileName"), 32)
    }

    fun getJsonFromFile(fileName: String): String {
        return readFileToString(TestUtil::class.java, "/$fileName")
    }

    private fun readFileToString(contextClass: Class<*>, streamIdentifier: String): String {
        val inputStreamReader = InputStreamReader(contextClass.getResourceAsStream(streamIdentifier)!!, Charset.defaultCharset())
        try {
            val stringBuilder = StringBuilder()
            BufferedReader(inputStreamReader).use { reader ->
                var nextLine = reader.readLine()
                while (nextLine != null) {
                    stringBuilder.append(nextLine)
                    nextLine = reader.readLine()
                }
            }
            return stringBuilder.toString()
        } catch (ioException: IOException) {
            throw ioException
        } finally {
            inputStreamReader.close()
        }
    }

    fun getPictureResponseDto(fileName: String): List<PhotoResponseDto> {
        val jsonString = readFileToString(TestUtil::class.java,"/$fileName")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, PhotoResponseDto::class.java)
        val adapter = moshi.adapter<List<PhotoResponseDto>>(type)
        return adapter.nullSafe().fromJson(jsonString)!!
    }

}

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}

inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> =
    ArgumentCaptor.forClass(T::class.java)

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()