package com.android.domain.repository

import com.android.data.dto.PhotoResponseDto
import com.android.data.remote.PicSumRemoteSource
import com.android.common.util.Resource
import javax.inject.Inject

interface PicSumRepository {

    suspend fun getPicSumPhotoList(page: Int, limit: Int): Resource<List<PhotoResponseDto>>

}

class PicSumRepositoryImpl @Inject constructor(
    private val picSumRemoteSource: PicSumRemoteSource
) : PicSumRepository {

    override suspend fun getPicSumPhotoList(page: Int, limit: Int): Resource<List<PhotoResponseDto>> {
        return picSumRemoteSource.getPicSumPhotoList(page, limit)
    }

}