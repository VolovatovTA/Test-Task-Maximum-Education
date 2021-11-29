package com.example.testtaskmaximumeducation.dagger

import android.app.Application
import com.example.testtaskmaximumeducation.DetailsArticleFragment
import com.example.testtaskmaximumeducation.ListArticleFragment
import com.example.testtaskmaximumeducation.MainActivity

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(details: DetailsArticleFragment)
    fun inject(listFragment: ListArticleFragment)
    fun inject(mainActivity: MainActivity)

}
class MyApplication: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}