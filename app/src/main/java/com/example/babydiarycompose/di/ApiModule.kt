package com.example.babydiarycompose.di

import com.example.babydiarycompose.BuildConfig
import com.example.babydiarycompose.api.PostsApi
import com.example.babydiarycompose.api.UserApi
import com.example.babydiarycompose.infrastructure.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideApiClient(baseUrl:) = ApiClient(baseUrl)

    @Provides
    fun provideUserApi(client: ApiClient): UserApi = client.createService(UserApi::class.java)

    @Provides
    fun providePostsApi(client: ApiClient): PostsApi = client.createService(PostsApi::class.java)
}