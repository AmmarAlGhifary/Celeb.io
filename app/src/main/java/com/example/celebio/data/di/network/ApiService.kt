package com.example.celebio.data.di.network

import com.example.celebio.data.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("articles")
    suspend fun getArticles() : Response<ArticlesResponse>
}