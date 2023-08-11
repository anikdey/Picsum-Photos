package com.android.data.remote

import com.android.data.api.PicSumApi
import com.android.data.util.MockRetrofitProvider
import com.android.common.util.Resource
import com.android.data.util.TestUtil.mockResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class PicSumRemoteSourceImplTest {

    @Mock
    private lateinit var picSumRemoteSource: PicSumRemoteSource

    @get: Rule
    val server = MockWebServer()

    @Before
    fun setUp() {
        val retrofit = MockRetrofitProvider.getRetrofitClient(server.url("/"))
        val authApi = retrofit.create(PicSumApi::class.java)
        picSumRemoteSource = PicSumRemoteSourceImpl(authApi, Dispatchers.IO)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun get_Picture_List_Returns_Data() = runBlocking {
        server.enqueue(mockResponse("picture_response.json").setResponseCode(200))
        val response = picSumRemoteSource.getPicSumPhotoList(1, 10)
        assertThat(response).isInstanceOf(Resource::class.java)
        assertThat(response is Resource.Success).isTrue()
        (response as Resource.Success).responseData.run {
            assertThat(this.size).isEqualTo(10)
        }
    }

    @Test
    fun get_Picture_List_Returns_Error() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(400).setBody("Something went wrong!"))
        val response = picSumRemoteSource.getPicSumPhotoList(1, 10)
        assertThat(response).isInstanceOf(Resource::class.java)
        assertThat(response is Resource.Error).isTrue()
        (response as Resource.Error).run {
            assertThat(code).isEqualTo(400)
        }
    }

}