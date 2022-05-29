package com.example.stakanchik.data.network

import com.example.stakanchik.data.models.ArticlesDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface ArticlesApi {
    @GET("articles")
    fun getArticles(): Single<List<ArticlesDto>>

    @GET("articles?where=is_marked=true")
    fun getFavouriteArticles(
    ): Single<List<ArticlesDto>>

    @GET("articles?sortBy=%60views%60%20desc")
    fun getPopularArticles(
    ): Single<List<ArticlesDto>>

    @GET("articles?where=is_read=true")
    fun getReadArticles(): Single<List<ArticlesDto>>

    @PUT("articles")
    fun updateViewsAndIsRead(@Body articlesDto: ArticlesDto): Single<ArticlesDto>

    @PUT("articles")
    fun updateIsMarked(@Body articlesDto: ArticlesDto): Single<ArticlesDto>
}