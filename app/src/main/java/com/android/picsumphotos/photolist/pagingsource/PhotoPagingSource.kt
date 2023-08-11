package com.android.picsumphotos.photolist.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.common.util.Resource
import com.android.domain.model.PhotoModel
import com.android.domain.usecase.GetPictureListUseCase

class PhotoPagingSource constructor(
    private val getPictureListUseCase: GetPictureListUseCase
) : PagingSource<Int, PhotoModel>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoModel> {
        val page = params.key ?: 1
        val limit = params.loadSize

        return try{
             when(val response = getPictureListUseCase.getPicSumPhotoList(page, limit)) {
                is Resource.Success -> LoadResult.Page(
                    data = response.responseData,
                    prevKey = if(page==1) null else page-1,
                    nextKey = if(response.responseData.isEmpty()) null else page+1
                )
                is Resource.Error -> LoadResult.Error(response.exception)
            }
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }
}