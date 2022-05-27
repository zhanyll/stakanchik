package com.example.stakanchik.domain.useCase

import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import com.example.stakanchik.extentions.toArticleEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetPopularArticlesUseCase @Inject constructor(
    private val articlesRepo: ArticlesRepo
) {
    operator fun invoke(): Single<List<ArticlesEntity>> {
        return articlesRepo.getPopularArticlesFromApi()
            .subscribeOn(Schedulers.io())
            .map {
                it.map { it.toArticleEntity() }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}