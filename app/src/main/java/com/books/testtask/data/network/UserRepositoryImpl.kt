package com.books.testtask.data.network

import com.books.testtask.data.database.UserDao
import com.books.testtask.presentation.utils.toDomain
import com.books.testtask.presentation.utils.toEntity
import com.books.testtask.domen.entities.User
import com.books.testtask.domen.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
        private val userDao: UserDao
) : UserRepository {

    override suspend fun setUsers(users: List<User>) {
        val userEntities = users.map { it.toEntity() }
        userDao.insertUsers(userEntities)
    }

    override suspend fun getUsersFromDatabase(): List<User> {
        val userEntities = userDao.getAllUsers().map {
            it.toDomain()
        }
        return userEntities
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
            userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
