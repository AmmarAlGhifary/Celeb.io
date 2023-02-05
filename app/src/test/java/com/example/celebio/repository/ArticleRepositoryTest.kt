package com.example.celebio.repository

//
//class ArticlesRepositoryTest {
//    private lateinit var articlesRepository: ArticlesRepository
//    private lateinit var apiService: ApiService
//
//    @Before
//    fun setUp() {
//        apiService = mock(ApiService::class.java)
//        articlesRepository = ArticlesRepository(apiService)
//    }
//
//    @Test
//    fun `getArticles returns success when API call is successful`() = runBlocking {
//        val articlesResponse = ArticlesResponse(listOf(Articles(...)))
//        val response = Response.success(articlesResponse)
//        `when`(apiService.getArticles()).thenReturn(response)
//
//        val result = articlesRepository.getArticles()
//
//        assertThat(result).isInstanceOf(Resource.Success::class.java)
//        assertThat((result as Resource.Success).data.toString()).isEqualTo(articlesResponse)
//    }
//
//    @Test
//    fun `getArticles returns error when API call returns error`() = runBlocking {
//        val response = Response.error<ArticlesResponse>(400, ResponseBody.create("application/json".toMediaTypeOrNull(), ""))
//        `when`(apiService.getArticles()).thenReturn(response)
//
//        val result = articlesRepository.getArticles()
//
//        assertThat(result.toString()).isInstanceOf(Resource.Error::class.java)
//        assertThat((result as Resource.Error).message).isEqualTo("Bad Request")
//    }
//
//    @Test
//    fun `getArticles returns error when exception is thrown`() = runBlocking {
//        `when`(apiService.getArticles()).thenThrow(IOException("Network error"))
//
//        val result = articlesRepository.getArticles()
//
//        assertThat(result).isInstanceOf(Resource.Error::class.java)
//        assertThat((result as Resource.Error).message).isEqualTo("Network error")
//    }
//}
