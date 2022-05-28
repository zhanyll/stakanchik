package com.example.stakanchik.domain.useCase

import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.extentions.toArticle
import com.example.stakanchik.extentions.toArticleEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetReadArticlesUseCase @Inject constructor(
    private val articlesRepo: ArticlesRepo
) {
    operator fun invoke(): Single<List<Article>> {
        return articlesRepo.getReadArticles()
            .subscribeOn(Schedulers.io())
            .map {
                it.map { it.toArticle() }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}