package com.example.stakanchik.domain.useCase

import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetArticleByIdUseCase @Inject constructor(
    private val articlesRepo: ArticlesRepo
) {

    operator fun invoke(id: Long): Single<ArticlesEntity> {
        return articlesRepo.getArticleById(id)
            .map {
                Thread.sleep(2000)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}