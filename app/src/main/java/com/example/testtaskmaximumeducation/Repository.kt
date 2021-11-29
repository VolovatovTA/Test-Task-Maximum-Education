package com.example.testtaskmaximumeducation

import com.example.testtaskmaximumeducation.NetworkApi.Article
import javax.inject.Inject

class Repository @Inject constructor(val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource){

    var listArticle = ArrayList<Article>()


}
