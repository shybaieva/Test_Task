package com.books.testtask.presentation.di

import com.books.testtask.data.database.NetworkRepositoryImpl
import com.books.testtask.data.network.Api
import com.books.testtask.domen.repositories.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(api: Api): NetworkRepository {
        return NetworkRepositoryImpl(api)
    }
}