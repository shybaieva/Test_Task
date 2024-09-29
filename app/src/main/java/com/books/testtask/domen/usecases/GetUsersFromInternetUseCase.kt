package com.books.testtask.domen.usecases

import com.books.testtask.domen.entities.User
import com.books.testtask.domen.repositories.NetworkRepository
import kotlinx.coroutines.flow.Flow

class GetUsersFromInternetUseCase(private val repository: NetworkRepository) {
    operator fun invoke(): Flow<List<User>> {
        return repository.getUsersFromInternet()
    }
}

