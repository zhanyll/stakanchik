package com.example.stakanchik.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.stakanchik.R
import com.example.stakanchik.Stakanchik
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.domain.useCase.GetArticlesAsLiveDataUseCase
import com.example.stakanchik.domain.useCase.GetArticleUseCase
import com.example.stakanchik.ui.base.AuthEvent
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseViewModel
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getArticleUseCase: GetArticleUseCase,
    private val getArticlesAsLiveDataUseCase: GetArticlesAsLiveDataUseCase,
    private val articlesRepo: ArticlesRepo
): BaseViewModel() {

    private val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>>
    get() = _article

    val articlesLiveData: LiveData<List<Any>> =
        Transformations.map(getArticlesAsLiveDataUseCase()){
            val newList = mutableListOf<Any>()

            it.forEachIndexed { index, articleEntity ->
                newList.add(articleEntity)
            }
            return@map newList
        }


//    init {
//        getArticle()
////        loadCharacters()
//    }
    fun getArticle() {
        disposable.add(
            getArticleUseCase()
                .subscribe({
                    Log.d("Article Success", it.toString())
                    try {
                        _article.postValue(it)
                        _article.value  = it
                    }catch (e: Throwable){
                        val a = e
                    }

                }, {
                    Log.d("Article Error", it.toString())
                    _event.value = BaseEvent.ShowToast(it.message ?: "")
                })
        )
    }

    fun loadCharacters() {
        _event.value = AuthEvent.ShowLoading
        disposable.add(
            getArticleUseCase()
                .doOnTerminate { _event.value = AuthEvent.StopLoading }
                .subscribe({


                }, {
                    handleError(it)
                })
        )
    }

    fun getArticleByIndex(index: Int): ArticlesEntity {
        return articlesLiveData.value?.get(index) as ArticlesEntity
    }


    override fun onCleared() {
        super.onCleared()
    }

    private fun handleError(it: Throwable) {
        _event.value = when (it) {
            is UnknownHostException -> BaseEvent.ShowToast(resources.getString(R.string.no_internet))
            else -> BaseEvent.ShowToast(resources.getString(R.string.unknown_error))
        }
    }
}