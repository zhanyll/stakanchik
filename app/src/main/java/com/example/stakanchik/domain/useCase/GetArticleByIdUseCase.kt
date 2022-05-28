package com.example.stakanchik.domain.useCase

import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.extentions.toArticle
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetArticleByIdUseCase @Inject constructor(
    private val articlesRepo: ArticlesRepo
) {

    operator fun invoke(objectId: String): Single<Article> {
        return articlesRepo.getArticleById(objectId)
            .map {
                it.toArticle()
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}