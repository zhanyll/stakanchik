package com.example.stakanchik.di

import android.app.Application
import androidx.room.Room
import com.example.stakanchik.data.storage.StakanchikDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
    @Provides
    fun provideArticleDao(stakanchikDataBase: StakanchikDataBase) = stakanchikDataBase.articlesDao()

    @Provides
    @Singleton
    fun provideStakanchikDatabase(context: Application) =
        Room.databaseBuilder(
            context,
            StakanchikDataBase::class.java,
            StakanchikDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
}