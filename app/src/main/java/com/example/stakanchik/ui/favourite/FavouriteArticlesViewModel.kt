package com.example.stakanchik.ui.favourite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stakanchik.R
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.domain.useCase.GetFavouriteArticlesUseCase
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseViewModel
import com.example.stakanchik.ui.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class FavouriteArticlesViewModel @Inject constructor(
    private val getFavouriteArticlesUseCase: GetFavouriteArticlesUseCase
): BaseViewModel() {

    private val _article = MutableLiveData<List<ArticlesEntity>>()
    val article: LiveData<List<ArticlesEntity>>
        get() = _article

    init {
        loadArticles()
    }

    fun getFavouriteArticles() {
        disposable.add(
            getFavouriteArticlesUseCase()
                .subscribe({ item ->
                    Log.d("Article Success", item.toString())
                    try {
                        _article.postValue(item)
                        _article.value  = item
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
            getFavouriteArticlesUseCase()
                .doOnTerminate { _event.value = Event.StopLoading }
                .subscribe({
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