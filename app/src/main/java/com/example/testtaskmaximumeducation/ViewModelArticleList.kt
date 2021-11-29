package com.example.testtaskmaximumeducation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelArticleList @Inject constructor(val repository: Repository): ViewModel(){
    var isGrid = false
    var columnCount = 1


    enum class ListArticleFormState{
        DATA_LOADING, ALL_IS_GOOD, NETWORK_ERROR
    }
    private val _listArticleFormState = MutableLiveData<ListArticleFormState>()
    val listArticleFormState: LiveData<ListArticleFormState> = _listArticleFormState


    fun loadArticlesList(){
        _listArticleFormState.value = ListArticleFormState.DATA_LOADING
        CoroutineScope(Main).launch {
            val response = repository.remoteDataSource.loadArticlesList()
            if (response.isSuccessful){
                repository.listArticle.addAll(response.body()!!)
                _listArticleFormState.value = ListArticleFormState.ALL_IS_GOOD
            }
            else
            {
                _listArticleFormState.value = ListArticleFormState.NETWORK_ERROR
            }
        }
    }

    fun refreshArticlesList() {
        _listArticleFormState.value = ListArticleFormState.DATA_LOADING
        CoroutineScope(Main).launch {
            val response = repository.remoteDataSource.loadArticlesList()
            if (response.isSuccessful){
                repository.listArticle.clear()
                repository.listArticle.addAll(response.body()!!)
                _listArticleFormState.value = ListArticleFormState.ALL_IS_GOOD
            }
            else
            {
                _listArticleFormState.value = ListArticleFormState.NETWORK_ERROR
            }
        }
    }


}
