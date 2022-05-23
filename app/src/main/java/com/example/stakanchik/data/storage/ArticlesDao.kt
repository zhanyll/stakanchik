package com.example.stakanchik.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.stakanchik.data.models.ArticlesEntity
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET

@Dao
interface ArticlesDao {
    @Insert
    fun insertArticles(articles: List<ArticlesEntity>): Completable

    @Query("SELECT * FROM ArticlesEntity")
    fun getAll(): LiveData<List<ArticlesEntity>>

    @Query("SELECT * FROM ArticlesEntity WHERE article_id = :article_id")
    fun getArticleByID(article_id: Int?): Single<ArticlesEntity>

}