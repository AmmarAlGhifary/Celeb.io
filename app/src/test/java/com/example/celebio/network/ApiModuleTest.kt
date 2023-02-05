package com.example.celebio.network


import com.example.celebio.BuildConfig
import com.example.celebio.data.di.network.ApiModule
import com.example.celebio.data.di.network.ApiService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiModuleTest {

    @Test
    fun test_provide_retrofit_instance() {
        val retrofit = Mockito.mock(Retrofit::class.java)
        val retrofitBuilder = Mockito.mock(Retrofit.Builder::class.java)
        Mockito.`when`(retrofitBuilder.baseUrl(BuildConfig.BASE_URL)).thenReturn(retrofitBuilder)
        Mockito.`when`(retrofitBuilder.client(ApiModule.buildRetrofitClient())).thenReturn(retrofitBuilder)
        Mockito.`when`(retrofitBuilder.addConverterFactory(GsonConverterFactory.create())).thenReturn(retrofitBuilder)
        Mockito.`when`(retrofitBuilder.build()).thenReturn(retrofit)
        Mockito.`when`(retrofit.create(ApiService::class.java)).thenReturn(Mockito.mock(ApiService::class.java))

        val apiService = ApiModule.provideRetrofitInstance()

        Mockito.verify(retrofitBuilder).baseUrl(BuildConfig.BASE_URL)
        Mockito.verify(retrofitBuilder).client(ApiModule.buildRetrofitClient())
        Mockito.verify(retrofitBuilder).addConverterFactory(GsonConverterFactory.create())
        Mockito.verify(retrofit).create(ApiService::class.java)
        assertNotNull(apiService)
    }

    @Test
    fun `check if base url is correct`() {
        assertEquals(BuildConfig.BASE_URL, "https://example.com")
    }
}
