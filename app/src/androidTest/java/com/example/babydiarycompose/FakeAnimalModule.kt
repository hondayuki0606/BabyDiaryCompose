package com.example.babydiarycompose

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.every
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AnimalModule::class]
)
class FakeAnimalModule {
    @Provides
    fun provideAnimal(): String {
        return mockk<String>().also {
            every { "it.bow()" } returns "100"
        }
    }
}