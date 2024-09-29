package com.books.testtask.data.network

import com.books.testtask.data.entities.UserDto
import retrofit2.http.GET

interface Api {

    @GET("users")
    suspend fun getUsers(): List<UserDto>
}