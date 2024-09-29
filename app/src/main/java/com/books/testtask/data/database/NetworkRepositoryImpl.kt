package com.books.testtask.data.database

import com.books.testtask.presentation.utils.toDomain
import com.books.testtask.domen.entities.User
import com.books.testtask.data.network.Api
import com.books.testtask.domen.repositories.NetworkRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NetworkRepositoryImpl(
        private val api: Api
) : NetworkRepository {
    override fun getUsersFromInternet(): Flow<List<User>> = flow {
        try {
            val users = api.getUsers().map { it.toDomain() }
            emit(users)
        } catch (e: HttpException) {
        } catch (e: IOException) {
        }
    }
}