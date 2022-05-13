package com.example.stakanchik.data.repo

import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.data.network.ArticlesApi
import com.example.stakanchik.data.storage.ArticlesDao
import javax.inject.Inject

class ArticlesRepo @Inject constructor(
    private var articlesDao: ArticlesDao,
    private var articlesApi: ArticlesApi
) {
    fun getArticleFromApi() = articlesApi.getArticles()

    fun saveArticlesToDataBase(articles: List<ArticlesEntity>) = articlesDao.insertArticles(articles)
}