package com.example.testtaskmaximumeducation.dagger

import com.example.testtaskmaximumeducation.LocalDataSource
import com.example.testtaskmaximumeducation.RemoteDataSource
import com.example.testtaskmaximumeducation.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): Repository{
        return Repository(remoteDataSource, localDataSource)
    }
}