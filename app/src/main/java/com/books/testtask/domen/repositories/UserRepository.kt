package com.books.testtask.domen.repositories

import com.books.testtask.domen.entities.User

interface UserRepository {
    suspend fun setUsers(users: List<User>)
    suspend fun getUsersFromDatabase(): List<User>
}
