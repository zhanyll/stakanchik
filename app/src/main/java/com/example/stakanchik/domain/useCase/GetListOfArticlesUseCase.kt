package com.example.stakanchik.domain.useCase

import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import com.example.stakanchik.extentions.toArticleEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetListOfArticlesUseCase @Inject constructor(
    private val articlesRepo: ArticlesRepo
) {

    operator fun invoke(): Single<List<ArticlesEntity>> {
        return articlesRepo.getArticleFromApi()
            .map {
                val listEp = mutableListOf<ArticlesEntity>()
                it.forEach {
                    listEp.add(it.toArticleEntity())
                }
                listEp.toList()
            }
            .map {
                articlesRepo.saveArticlesToDataBase(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}