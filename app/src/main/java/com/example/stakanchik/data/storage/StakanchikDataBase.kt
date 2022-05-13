package com.example.stakanchik.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stakanchik.data.models.ArticlesEntity

@Database(entities = [ArticlesEntity::class], version = 1)
abstract class StakanchikDataBase: RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    companion object {
        const val DATABASE_NAME = "stakanchik.db"
    }
}