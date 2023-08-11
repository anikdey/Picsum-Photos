package com.android.domain.usecase

import com.android.common.util.Resource
import com.android.domain.model.PhotoModel
import com.android.domain.model.toPhotoModelList
import com.android.domain.repository.PicSumRepository
import javax.inject.Inject

interface GetPictureListUseCase {

    suspend fun getPicSumPhotoList(page: Int, limit: Int): Resource<List<PhotoModel>>

}

class GetPictureListUseCaseImpl @Inject constructor(
    private val picSumRepository: PicSumRepository
) : GetPictureListUseCase {

    override suspend fun getPicSumPhotoList(page: Int, limit: Int): Resource<List<PhotoModel>> {
        return when (val response = picSumRepository.getPicSumPhotoList(page, limit)) {
            is Resource.Success -> {
                Resource.Success(response.responseData.toPhotoModelList())
            }
            is Resource.Error -> {
                Resource.Error(response.exception, response.code)
            }
        }
    }

}