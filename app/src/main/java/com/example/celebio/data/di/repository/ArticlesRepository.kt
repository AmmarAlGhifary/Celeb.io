package com.example.celebio.data.di.repository

import android.util.Log
import com.example.celebio.data.model.ArticlesResponse
import com.example.celebio.data.di.network.ApiService
import com.example.celebio.utils.Resource
import java.io.IOException
import javax.inject.Inject

class ArticlesRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getArticles(): Resource<ArticlesResponse> {
        return try {
            val response = apiService.getArticles()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                when (response.code()) {
                    400 -> Resource.Error(IOException("Bad Request"))
                    401 -> Resource.Error(IOException("Unauthorized"))
                    403 -> Resource.Error(IOException("Forbidden"))
                    404 -> Resource.Error(IOException("Not Found"))
                    500 -> Resource.Error(IOException("Internal Server Error"))
                    else -> Resource.Error(IOException("Unknown error: ${response.code()}"))
                }
                Log.e(" articlesRepository", "Articles getting quotes: ${response.code()}")
                Resource.Error(IOException("Error code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e(" articleRepository", "Articles getting quotes: ${e.message}")
            Resource.Error(e)
        }
    }
}