package com.example.stakanchik.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stakanchik.R
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.domain.useCase.GetArticleUseCase
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseViewModel
import com.example.stakanchik.ui.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Array.get
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class PopularArticlesViewModel @Inject constructor(
    private val getArticleUseCase: GetArticleUseCase
): BaseViewModel() {

    private val _article = MutableLiveData<List<ArticlesEntity>>()
    val article: LiveData<List<ArticlesEntity>>
        get() = _article

    init {
        loadArticles()
    }

    fun getArticle() {
        disposable.add(
            getArticleUseCase()
                .subscribe({ item ->
                    Log.d("Article Success", item.toString())
                    try {
                        _article.postValue(item)
                        _article.value  = item.sortedByDescending { it.views }
                    }catch (e: Throwable){
                        val a = e
                    }
                }, {
                    Log.d("Article Error", it.toString())
                    _event.value = BaseEvent.ShowToast(it.message ?: "")
                })
        )
    }

    fun loadArticles() {
        _event.value = Event.ShowLoading
        disposable.add(
            getArticleUseCase()
                .doOnTerminate { _event.value = Event.StopLoading }
                .subscribe({
//                           it.sortedByDescending { it.views }
                }, {
                    handleError(it)
                })
        )
    }

    private fun handleError(it: Throwable) {
        _event.value = when (it) {
            is UnknownHostException -> BaseEvent.ShowToast(resources.getString(R.string.no_internet))
            else -> BaseEvent.ShowToast(resources.getString(R.string.unknown_error))
        }
    }
}