package com.example.stakanchik.data.network

import com.example.stakanchik.data.models.ArticlesDto
import io.reactivex.Single
import retrofit2.http.GET

interface ArticlesApi {
    @GET("articles")
    fun getArticles(): Single<List<ArticlesDto>>
}