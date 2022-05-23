package com.example.stakanchik.data.network

import com.example.stakanchik.data.models.ArticlesDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {
    @GET("articles")
    fun getArticles(): Single<List<ArticlesDto>>

    @GET("articles")
    fun getArticleByIdFromApi(
        @Query("objectId") objectId: String
    ): Single<List<ArticlesDto>>
}