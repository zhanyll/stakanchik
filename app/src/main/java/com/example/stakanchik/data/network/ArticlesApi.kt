package com.example.stakanchik.data.network

import com.example.stakanchik.data.models.ArticlesDto
import io.reactivex.Single
import retrofit2.Call
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

//
//    @PUT("articles/{objectId}")
//    fun updateData(
//        @Path("objectId") objectId: String,
//        @Field("views") views: Int,
//        @Field("is_marked") is_marked: Boolean,
//        @Field("is_read") is_read: Boolean
//    ): Single<List<ArticlesDto>>
}