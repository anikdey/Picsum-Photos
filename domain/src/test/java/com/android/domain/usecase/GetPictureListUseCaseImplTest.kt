package com.android.domain.usecase

import com.android.common.util.Resource
import com.android.data.dto.PhotoResponseDto
import com.android.domain.model.PhotoModel
import com.android.domain.model.toPhotoModel
import com.android.domain.util.TestUtil
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

class GetPictureListUseCaseImplTest {

    private val page = 1
    private val limit = 10

    @Mock
    lateinit var mockUseCase: GetPictureListUseCase

    lateinit var response: List<PhotoResponseDto>
    lateinit var photoModelList: ArrayList<PhotoModel>
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        response = TestUtil.getPictureResponseDto("picture_response.json")
        photoModelList = ArrayList()
    }

    @After
    fun tearDown() {}

    @Test
    fun get_Word_Items_Returns_Success() = runBlocking {
        response.forEach {
            photoModelList.add(it.toPhotoModel())
        }
        Mockito.`when`(mockUseCase.getPicSumPhotoList(page, limit)).thenReturn(Resource.Success(photoModelList))
        val response = mockUseCase.getPicSumPhotoList(page, limit)
        Truth.assertThat(response is Resource.Success).isTrue()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun get_Pic_Sum_Photo_List_Returns_Correct_Count() = runTest {
        response.forEach {
            photoModelList.add(it.toPhotoModel())
        }
        Mockito.`when`(mockUseCase.getPicSumPhotoList(page, limit)).thenReturn(Resource.Success(photoModelList))
        when(val response = mockUseCase.getPicSumPhotoList(page, limit)) {
            is Resource.Success -> {
                Truth.assertThat(response.responseData.size).isEqualTo(photoModelList.size)
            }
            else -> {}
        }
    }

    @Test
    fun get_Pic_Sum_Photo_List_Returns_Error() = runTest {
        val error = IOException("No internet connection")
        Mockito.`when`(mockUseCase.getPicSumPhotoList(page, limit)).thenReturn(Resource.Error(error, 400))
        when(val response = mockUseCase.getPicSumPhotoList(page, limit)) {
            is Resource.Error -> {
                Truth.assertThat(response.exception).isEqualTo(error)
            }
            else -> {}
        }
    }


}