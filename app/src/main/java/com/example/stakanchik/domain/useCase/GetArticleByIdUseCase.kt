package com.example.stakanchik.domain.useCase

import com.example.stakanchik.data.repo.ArticlesRepo
import javax.inject.Inject

class GetArticleByIdUseCase @Inject constructor(
    private val articlesRepo: ArticlesRepo
) {

    
}