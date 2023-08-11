package com.android.data.api

import com.android.data.util.MockRetrofitProvider
import com.android.data.util.TestUtil.mockResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PicSumApiTest {

    private lateinit var picSumApi: PicSumApi

    @get: Rule
    val server = MockWebServer()

    @Before
    fun setUp() {
        val retrofit = MockRetrofitProvider.getRetrofitClient(server.url("/"))
        picSumApi = retrofit.create(PicSumApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun get_Photo_List_Called_With_GET_Method() = runBlocking {
        server.enqueue(mockResponse("picture_response.json"))
        picSumApi.getPicSumPhotoList(1, 10)
        val request = server.takeRequest()
        Truth.assertThat(request.method).isEqualTo("GET")
    }

    @Test
    fun correct_End_Point_Is_Called() = runBlocking {
        val page = 1
        val limit = 10
        server.enqueue(mockResponse("picture_response.json").setResponseCode(200))
        picSumApi.getPicSumPhotoList(page, limit)
        val request = server.takeRequest()
        Truth.assertThat("/v2/list?page=${page}&limit=${limit}").isEqualTo(request.path)
    }

}