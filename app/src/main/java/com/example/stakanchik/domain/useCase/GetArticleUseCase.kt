package com.example.stakanchik.domain.useCase

import android.util.Log
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.extentions.toArticle
import com.example.stakanchik.extentions.toArticleEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val articlesRepo: ArticlesRepo
) {
    operator fun invoke(): Single<List<Article>> {
        return articlesRepo.getArticleFromApi()
            .subscribeOn(Schedulers.io())
            .map {
                Log.d("Article", it.toString())
                articlesRepo.saveArticlesToDataBase(it.map { it.toArticleEntity() })
                it.map { it.toArticle() }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}