package com.books.testtask.presentation.di

import com.books.testtask.domen.repositories.NetworkRepository
import com.books.testtask.domen.repositories.UserRepository
import com.books.testtask.domen.usecases.GetUsersFromInternetUseCase
import com.books.testtask.domen.usecases.SetUsersToDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUsersFromInternetUseCase(repository: NetworkRepository): GetUsersFromInternetUseCase {
        return GetUsersFromInternetUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSetUsersToDatabaseUseCase(
            userRepository: UserRepository
    ): SetUsersToDatabaseUseCase {
        return SetUsersToDatabaseUseCase(userRepository)
    }
}

