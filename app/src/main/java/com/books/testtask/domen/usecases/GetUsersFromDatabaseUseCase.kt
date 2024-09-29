package com.books.testtask.domen.usecases

import com.books.testtask.domen.entities.User
import com.books.testtask.domen.repositories.UserRepository
import javax.inject.Inject

class GetUsersFromDatabaseUseCase @Inject constructor(
        private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return userRepository.getUsersFromDatabase()
    }
}
