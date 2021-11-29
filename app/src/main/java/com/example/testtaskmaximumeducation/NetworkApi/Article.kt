package com.example.testtaskmaximumeducation.NetworkApi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Article (
    @SerializedName("id")
    @Expose
    val id: Int,
//    @SerializedName("featured")
//    @Expose
//    val featured: Boolean = false,
    @SerializedName("title")
    @Expose
    val title: String,
//    @SerializedName("url")
//    @Expose
//    val url: String,
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String,
    @SerializedName("newsSite")
    @Expose
    val newsSite: String,
    @SerializedName("summary")
    @Expose
    val summary: String,
    @SerializedName("publishedAt")
    @Expose
    val publishDate: Date
//    @SerializedName("launches")
//    @Expose
//    val launches: List<Launches>,
//    @SerializedName("events")
//    @Expose
//    val events: List<String>
    )
