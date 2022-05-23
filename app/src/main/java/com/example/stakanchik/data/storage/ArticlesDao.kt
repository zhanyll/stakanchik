package com.example.stakanchik.data.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.stakanchik.data.models.ArticlesEntity
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET

@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticlesEntity>)

    @Query("SELECT * FROM ArticlesEntity")
    fun getAll(): LiveData<List<ArticlesEntity>>

    @Query("SELECT * FROM ArticlesEntity WHERE objectId = :objectId")
    fun getArticleByID(objectId: String): Single<ArticlesEntity>

    @Query("SELECT * FROM ArticlesEntity WHERE is_marked = 'true'")
    fun getFavouriteArticles(): LiveData<List<ArticlesEntity>>
}