package com.example.stakanchik.domain.useCase

import androidx.lifecycle.LiveData
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.repo.ArticlesRepo
import javax.inject.Inject

class GetArticlesAsLiveDataUseCase @Inject constructor(
    private val articlesRepo: ArticlesRepo
) {

    operator fun invoke(): LiveData<List<ArticlesEntity>> {
        return articlesRepo.getArticlesAsLive()
    }
}