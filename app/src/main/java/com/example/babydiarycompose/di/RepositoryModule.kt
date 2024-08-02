package com.example.babydiarycompose.di

import com.example.babydiarycompose.api.UserApi
import com.example.babydiarycompose.infrastructure.ApiClient
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
    fun provideEventRepository(): EventRepository = EventRepositoryImpl()

    @Provides
    fun provideUserApi(client: ApiClient): UserApi = client.createService(UserApi::class.java)
}