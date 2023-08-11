package com.android.data.remote

import com.android.common.qualifiers.IoDispatcher
import com.android.data.api.PicSumApi
import com.android.data.dto.PhotoResponseDto
import com.android.data.util.BaseSource
import com.android.common.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface PicSumRemoteSource {
    suspend fun getPicSumPhotoList(page: Int, limit: Int): Resource<List<PhotoResponseDto>>
}

class PicSumRemoteSourceImpl @Inject constructor(
    private val piSumApi: PicSumApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseSource(), PicSumRemoteSource {

    override suspend fun getPicSumPhotoList(page: Int, limit: Int): Resource<List<PhotoResponseDto>> {
        return safeApiCall(ioDispatcher){ piSumApi.getPicSumPhotoList(page, limit) }
    }
}