package com.example.celebio.viewModel

import com.example.celebio.data.di.repository.ArticlesRepository
import com.example.celebio.data.model.ArticlesResponse
import com.example.celebio.utils.Resource
import com.example.celebio.view.articles.viewmodel.ArticleViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import net.bytebuddy.implementation.FixedValue.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticleViewModelTest {

    @Mock
    lateinit var repository: ArticlesRepository

//    @get:Rule
//    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ArticleViewModel

    @Before
    fun setUp() {
        viewModel = ArticleViewModel(repository)
    }

    @Test
    fun `get articles success`() = runBlocking {
        val articlesResponse = ArticlesResponse()
        `when`(repository.getArticles()).thenReturn(Resource.Success(articlesResponse))

        viewModel.getArticles()
//        val result = viewModel.articlesResponse.getOrAwaitValue()
//        assertThat(result, `is`(articlesResponse))
        assertThat(viewModel.error.value, `is`(nullValue()))
    }

    @Test
    fun `get articles error`() = runBlocking {
        val errorMessage = "Error message"
        `when`(repository.getArticles()).thenReturn(Resource.Error(Exception(errorMessage)))
        viewModel.getArticles()
        assertThat(viewModel.articlesResponse.value, `is`(nullValue()))
//        assertThat(viewModel.error.getOrAwaitValue(), `is`("Error $errorMessage"))
    }
}


