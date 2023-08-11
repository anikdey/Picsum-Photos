package com.android.common.util

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val responseData: T) : Resource<T>()
    data class Error(val exception: Exception, val code: Int) : Resource<Nothing>()
}