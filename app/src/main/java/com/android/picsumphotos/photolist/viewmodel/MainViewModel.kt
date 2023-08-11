package com.android.picsumphotos.photolist.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.domain.model.PhotoModel
import com.android.domain.usecase.GetPictureListUseCase
import com.android.picsumphotos.common.Constants
import com.android.picsumphotos.photolist.pagingsource.PhotoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPictureListUseCase: GetPictureListUseCase
) : ViewModel() {

    var photosFlow: Flow<PagingData<PhotoModel>> = MutableSharedFlow()

    private val _darkMode = mutableStateOf(false)
    var darkMode = _darkMode

    private val _isLoading = mutableStateOf(true)
    var isLoading = _isLoading
    init {
        getPicSumPhotoList()
        splashDelay()
    }

    private fun splashDelay() {
        viewModelScope.launch {
            delay(1000)
            _isLoading.value = false
        }
    }

    fun toggleDarkMode() {
        _darkMode.value = !_darkMode.value
    }

    private fun getPicSumPhotoList() {
        photosFlow = Pager(
            config = PagingConfig(
                pageSize = Constants.pageSize,
                initialLoadSize = Constants.initialLoadSize,
                prefetchDistance = Constants.prefetchDistance,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PhotoPagingSource(getPictureListUseCase) }
        ).flow.cachedIn(viewModelScope)
    }
}
