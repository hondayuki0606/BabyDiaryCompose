package com.example.babydiarycompose.di

import com.example.babydiarycompose.repository.EventRepository
import com.example.babydiarycompose.repository.EventRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideEventRepository(): EventRepository {
        return EventRepositoryImpl()
    }
}