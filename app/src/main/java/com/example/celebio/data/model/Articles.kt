package com.example.celebio.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Articles(
    @SerializedName("content")
    val content: String,
    @SerializedName("contentThumbnail")
    val contentThumbnail: String,
    @SerializedName("contributorAvatar")
    val contributorAvatar: String,
    @SerializedName("contributorName")
    val contributorName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("slideshow")
    val slideshow: List<String>,
    @SerializedName("title")
    val title: String
) : Serializable