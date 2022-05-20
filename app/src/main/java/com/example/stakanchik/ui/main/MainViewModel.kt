package com.example.stakanchik.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.stakanchik.Stakanchik
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.domain.useCase.GetArtcilesAsLiveDataUseCase
import com.example.stakanchik.domain.useCase.GetArticleUseCase
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseViewModel
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getArticleUseCase: GetArticleUseCase
): BaseViewModel() {

    private val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>>
    get() = _article

    private val articlesRepo = ArticlesRepo(
        getApplication()<Stakanchik>.articlesDao()
    )
    private val getArticlesAsLiveDataUseCase = GetArtcilesAsLiveDataUseCase()

    val articlesLiveData: LiveData<List<Any>> =
        Transformations.map(getArticlesAsLiveDataUseCase()){
            val newList = mutableListOf<Any>()

            it.forEachIndexed { index, articleEntity ->
                newList.add(articleEntity)
            }
            return@map newList
        }

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

    fun getArtcileByIndex(index: Int): ArticlesEntity {
        return articlesLiveData.value?.get(index) as ArticlesEntity
    }
}