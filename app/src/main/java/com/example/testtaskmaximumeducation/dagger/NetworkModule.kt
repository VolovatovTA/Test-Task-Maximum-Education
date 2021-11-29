package com.example.testtaskmaximumeducation.dagger

import com.example.testtaskmaximumeducation.NetworkApi.NetworkApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): NetworkApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.spaceflightnewsapi.net/v3/")
            .build()
            .create(NetworkApi::class.java)
    }


}
