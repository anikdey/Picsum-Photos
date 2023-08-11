package com.android.data.util

import com.android.common.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

abstract class BaseSource {

    protected suspend fun <T : Any> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, call: suspend () -> Response<T>): Resource<T> {
        var response: Response<T>? = null
        return withContext(dispatcher) {
            try {
                response = call()
            } catch (ioException: IOException) {
                return@withContext Resource.Error(ConnectionException(), response?.code() ?: -1)
            } catch (nullPointer: NullPointerException) {
                Resource.Error(nullPointer, response?.code() ?: -1)
            } catch (throwable: Throwable) {
                return@withContext Resource.Error(
                    ExceptionHandler.parseHttpException(throwable),
                    response?.code() ?: -1
                )
            }
            response.let {res->
                res?.let {
                    if (it.isSuccessful) {
                        response?.body()?.let {
                            Resource.Success(it)
                        } ?: Resource.Error(EmptyResponseBodyException(), it.code())
                    } else{
                        Resource.Error(UnknownException(it.message()), it.code())
                    }
                } ?: Resource.Error(ConnectionException(), response?.code() ?: -1)
            }
        }
    }
}

