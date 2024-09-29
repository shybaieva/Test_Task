package com.books.testtask.domen.usecases

import com.books.testtask.domen.entities.User
import com.books.testtask.domen.repositories.UserRepository
import javax.inject.Inject

class SetUsersToDatabaseUseCase @Inject constructor(
        private val userRepository: UserRepository
) {
    suspend fun execute(users: List<User>) {
        userRepository.setUsers(users)
    }
}



