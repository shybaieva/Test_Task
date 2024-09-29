package com.books.testtask.domen.repositories

import com.books.testtask.domen.entities.User
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    fun getUsersFromInternet(): Flow<List<User>>
}