package com.example.testtaskmaximumeducation

import android.util.Log
import com.example.testtaskmaximumeducation.NetworkApi.Article
import javax.inject.Inject
import javax.inject.Singleton

class Repository @Inject constructor(val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource){

    val tag = "timofey"
    init {
        Log.d(tag, "init repository")
    }
    var listArticle = ArrayList<Article>()


}
