package com.android.data.api

import com.android.data.dto.PhotoResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PicSumApi {

    @GET("v2/list")
    suspend fun getPicSumPhotoList(@Query("page") page: Int, @Query("limit") limit: Int):
            Response<List<PhotoResponseDto>>

}
