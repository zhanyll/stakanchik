package com.example.stakanchik.data.network

import com.example.stakanchik.data.models.ArticlesDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {
    @GET("articles")
    fun getArticles(): Single<List<ArticlesDto>>

    @GET("articles?where=is_marked=true")
    fun getFavouriteArticles(
//        @Query("is_marked") is_marked: Boolean
    ): Single<List<ArticlesDto>>

    @GET("articles?sortBy=%60views%60%20desc")
    fun getPopularArticles(
//        @Query("views") views: Int
    ): Single<List<ArticlesDto>>

    @GET("articles")
    fun getArticleByIdFromApi(
        @Query("objectId") objectId: String
    ): Single<List<ArticlesDto>>
}