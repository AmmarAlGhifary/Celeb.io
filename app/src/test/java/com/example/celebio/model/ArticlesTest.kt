package com.example.celebio.model

import com.example.celebio.data.model.Articles
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ArticlesTest {

    @Test
    fun test_articles_properties() {
        val content = "Test content"
        val contentThumbnail = "Test content thumbnail"
        val contributorAvatar = "Test contributor avatar"
        val contributorName = "Test contributor name"
        val createdAt = "Test created at"
        val id = "Test id"
        val slideshow = listOf("Slideshow 1", "Slideshow 2")
        val title = "Test title"

        val articles = Articles(content, contentThumbnail, contributorAvatar, contributorName, createdAt, id, slideshow, title)

        assertEquals(content, articles.content)
        assertEquals(contentThumbnail, articles.contentThumbnail)
        assertEquals(contributorAvatar, articles.contributorAvatar)
        assertEquals(contributorName, articles.contributorName)
        assertEquals(createdAt, articles.createdAt)
        assertEquals(id, articles.id)
        assertEquals(slideshow, articles.slideshow)
        assertEquals(title, articles.title)
    }
}