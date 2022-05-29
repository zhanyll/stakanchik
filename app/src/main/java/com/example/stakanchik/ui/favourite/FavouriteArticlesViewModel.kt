package com.example.stakanchik.ui.favourite

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stakanchik.R
import com.example.stakanchik.data.models.ArticlesDto
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.network.ArticlesApi
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.domain.useCase.GetFavouriteArticlesUseCase
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseViewModel
import com.example.stakanchik.ui.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class FavouriteArticlesViewModel @Inject constructor(
    private val getFavouriteArticlesUseCase: GetFavouriteArticlesUseCase,
    private val articlesApi: ArticlesApi
): BaseViewModel() {

    private val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>>
        get() = _article

    init {
        loadArticles()
    }

    fun getFavouriteArticles() {
        disposable.add(
            getFavouriteArticlesUseCase()
                .subscribe({ item ->
                    try {
                        _article.value  = item
                    }catch (e: Throwable){
                        val a = e
                    }
                }, {
                    _event.value = BaseEvent.ShowToast(it.message ?: "")
                })
        )
    }

    fun loadArticles() {
        _event.value = Event.ShowLoading
        disposable.add(
            getFavouriteArticlesUseCase()
                .doOnTerminate { _event.value = Event.StopLoading }
                .subscribe({
                }, {
                    handleError(it)
                })
        )
    }

    @SuppressLint("CheckResult")
    fun updateArticleViewsAndIsRead(articlesDto: ArticlesDto) {
        articlesApi.updateViewsAndIsRead(articlesDto)
            .subscribe({
                Log.d("update", "success")
            }, {
                Log.d("error", "could not set new values")
            })
    }

    private fun handleError(it: Throwable) {
        _event.value = when (it) {
            is UnknownHostException -> BaseEvent.ShowToast(resources.getString(R.string.no_internet))
            else -> BaseEvent.ShowToast(resources.getString(R.string.unknown_error))
        }
    }
}