package com.example.babydiarycompose.di

import android.content.Context
import androidx.room.Room
import com.example.babydiarycompose.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
        .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideEventDao(db: AppDatabase) = db.eventDao()

    @Singleton
    @Provides
    fun provideDailyDiaryDao(db: AppDatabase) = db.dailyDiaryDao()
}