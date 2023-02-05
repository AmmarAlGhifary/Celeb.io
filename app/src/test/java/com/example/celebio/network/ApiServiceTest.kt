package com.example.celebio.network

import com.example.celebio.data.di.network.ApiService
import com.example.celebio.data.model.ArticlesResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {

    @Mock
    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = retrofit.create(ApiService::class.java)
    }

    @Test
    fun `test getArticles success`() = runBlocking {
        val articlesResponse = ArticlesResponse()
        val response = Response.success(articlesResponse)
        Mockito.`when`(apiService.getArticles()).thenReturn(response)

        val result = apiService.getArticles()

        assertEquals(result, response)
    }

    @Test
    fun `test getArticles error`() = runBlocking {
        val responseBody = ResponseBody.create(null, "Error")
        val response = Response.error<ArticlesResponse>(400, responseBody)
        Mockito.`when`(apiService.getArticles()).thenReturn(response)

        val result = apiService.getArticles()

        assertEquals(result, response)
    }
}