package com.example.babydiarycompose

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AnimalModule {
    @Provides
    fun provideAnimal(): String {
        return "dog"
    }
}