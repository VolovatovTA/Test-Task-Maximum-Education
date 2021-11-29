package com.example.testtaskmaximumeducation

import android.util.Log
import com.example.testtaskmaximumeducation.NetworkApi.Article
import com.example.testtaskmaximumeducation.NetworkApi.NetworkApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class RemoteDataSource @Inject constructor(val networkApi: NetworkApi){
    val tag = "timofey"

    suspend fun loadArticlesList(): Response<ArrayList<Article>> {
//        networkApi.getArticles().enqueue(object : Callback<ArrayList<Article>>{
//            override fun onResponse(
//                call: Call<ArrayList<Article>>,
//                response: Response<ArrayList<Article>>
//            ) {
//                Log.d(tag, "onResponse")
//                Log.d(tag, "response = ${response.message()}")
//            }
//
//            override fun onFailure(call: Call<ArrayList<Article>>, t: Throwable) {
//                Log.d(tag, "onFailure")
//                Log.d(tag, "t = $t")            }
//
//        })
        return networkApi.getArticles().awaitResponse()

    }

}
