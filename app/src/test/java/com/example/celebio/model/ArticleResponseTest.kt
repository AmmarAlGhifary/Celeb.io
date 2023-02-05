package com.example.celebio.model

import com.example.celebio.data.model.Articles
import com.example.celebio.data.model.ArticlesResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ArticleResponseTest {

    @Test
    fun test_articles_response() {
        val articles1 = Articles(
            "Content 1",
            "Content Thumbnail 1",
            "Contributor Avatar 1",
            "Contributor Name 1",
            "Created At 1",
            "Id 1",
            listOf("Slideshow 1"),
            "Title 1"
        )
        val articles2 = Articles(
            "Content 2",
            "Content Thumbnail 2",
            "Contributor Avatar 2",
            "Contributor Name 2",
            "Created At 2",
            "Id 2",
            listOf("Slideshow 2"),
            "Title 2"
        )

        val articlesResponse = ArticlesResponse()
        articlesResponse.add(articles1)
        articlesResponse.add(articles2)

        assertEquals(2, articlesResponse.size)
        assertEquals(articles1, articlesResponse[0])
        assertEquals(articles2, articlesResponse[1])

    }

}