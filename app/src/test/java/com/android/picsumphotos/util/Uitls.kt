package com.android.picsumphotos.util


import com.android.domain.model.PhotoModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset

object Utils {

    private fun readFileToString(contextClass: Class<*>, streamIdentifier: String): String {
        val inputStreamReader = InputStreamReader(contextClass.getResourceAsStream(streamIdentifier)!!,
            Charset.defaultCharset())
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


    fun getPhotoModelList(fileName: String): List<PhotoModel> {
        val jsonString = readFileToString(Utils::class.java,"/$fileName")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, PhotoModel::class.java)
        val adapter = moshi.adapter<List<PhotoModel>>(type)
        return adapter.nullSafe().fromJson(jsonString)!!
    }

}

class RequestException(
    override var message: String = "",
    var statusCode: Int = 0
) : Exception(message)