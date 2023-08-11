package com.android.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponseDto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @Json(name = "download_url") val downloadUrl: String
)
