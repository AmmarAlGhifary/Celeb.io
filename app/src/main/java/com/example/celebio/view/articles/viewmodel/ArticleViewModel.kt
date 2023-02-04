package com.example.celebio.view.articles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.celebio.data.model.ArticlesResponse
import com.example.celebio.data.di.repository.ArticlesRepository
import com.example.celebio.data.model.Articles
import com.example.celebio.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val repository: ArticlesRepository) : ViewModel() {

    private val _response : MutableLiveData<ArticlesResponse?> = MutableLiveData()
    val articlesResponse : LiveData<ArticlesResponse?>
        get() = _response

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String>
        get() = _error

    fun getArticles() = viewModelScope.launch {
        try {
            when (val articleResult = repository.getArticles()) {
                is Resource.Success -> {
                    _response.postValue(articleResult.data)
                }
                is Resource.Error -> {
                    _error.postValue("Error ${articleResult.exception.message}")
                    Log.e(" articlesViewModel", "Error getting articles: ${articleResult.exception.message}")
                }
            }
        } catch (e: Exception) {
            _error.postValue("Error: ${e.message}")
            Log.e(" articlesViewModel", "Error getting articles: ${e.message}")
        }
    }


}