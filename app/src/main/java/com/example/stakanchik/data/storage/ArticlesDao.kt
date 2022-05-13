package com.example.stakanchik.data.storage

import androidx.room.Dao
import androidx.room.Insert
import com.example.stakanchik.data.models.ArticlesEntity
import io.reactivex.Completable

@Dao
interface ArticlesDao {
    @Insert
    fun insertArticles(articles: List<ArticlesEntity>): Completable
}