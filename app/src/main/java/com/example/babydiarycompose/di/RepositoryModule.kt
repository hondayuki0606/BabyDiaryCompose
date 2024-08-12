package com.example.babydiarycompose.di

import com.example.babydiarycompose.api.PostsApi
import com.example.babydiarycompose.api.UserApi
import com.example.babydiarycompose.repository.EventRepository
import com.example.babydiarycompose.repository.EventRepositoryImpl
import com.example.babydiarycompose.repository.PostsRepository
import com.example.babydiarycompose.repository.PostsRepositoryImpl
import com.example.babydiarycompose.repository.UserRepository
import com.example.babydiarycompose.repository.UserRepositoryImpl
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
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepositoryImpl(userApi)
    }

    @Provides
    fun providePostsRepository(postsApi: PostsApi): PostsRepository {
        return PostsRepositoryImpl(postsApi)
    }
}