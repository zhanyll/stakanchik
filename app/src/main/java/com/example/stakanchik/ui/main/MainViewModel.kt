package com.example.stakanchik.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.domain.useCase.GetArticleUseCase
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getArticleUseCase: GetArticleUseCase
): BaseViewModel() {

    private val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>>
    get() = _article

    fun getArticle() {
        disposable.add(
            getArticleUseCase()
                .subscribe({
                    Log.d("Article Success", it.toString())
                    _article.value = it
                }, {
                    Log.d("Article Error", it.toString())
                    _event.value = BaseEvent.ShowToast(it.message ?: "")
                })
        )
    }
}