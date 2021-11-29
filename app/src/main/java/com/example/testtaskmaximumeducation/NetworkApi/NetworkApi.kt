package com.example.testtaskmaximumeducation.NetworkApi

import retrofit2.Call
import retrofit2.http.GET


interface NetworkApi {
    @GET("articles")
    fun getArticles(): Call<ArrayList<Article>>
}