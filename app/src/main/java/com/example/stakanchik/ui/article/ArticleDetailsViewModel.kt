package com.example.stakanchik.ui.article

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stakanchik.data.models.ArticlesDto
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.network.ArticlesApi
import com.example.stakanchik.data.repo.ArticlesRepo
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.domain.useCase.GetArticleByIdUseCase
import com.example.stakanchik.domain.useCase.GetArticleUseCase
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val articlesApi: ArticlesApi,
    private val repo: ArticlesRepo
): BaseViewModel() {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article>
        get() = _article

    fun getArticleById(objectId: String) {
        disposable.add(
            getArticleByIdUseCase(objectId)
                .subscribe({
                    _article.value = it
                    it
                }, {
                    _event.value = BaseEvent.ShowToast(it.message ?: "")
                })
        )
    }

    @SuppressLint("CheckResult")
    fun updateArticleViewsAndIsRead(articlesDto: ArticlesDto) {
        articlesApi.updateViewsAndIsRead(articlesDto)
            .subscribe({
                it.is_read = true
                it.views += 1
            }, {
                Log.d("error", "could not set new values")
            })
    }

    fun updateDaoData(articlesEntity: ArticlesEntity) {
        repo.updateViewsIsReadData(articlesEntity)
    }
}