package com.example.testtaskmaximumeducation

import com.example.testtaskmaximumeducation.NetworkApi.Article
import com.example.testtaskmaximumeducation.NetworkApi.NetworkApi
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val networkApi: NetworkApi){

    suspend fun loadArticlesList(): Response<ArrayList<Article>> {
        return networkApi.getArticles().awaitResponse()

    }

}
