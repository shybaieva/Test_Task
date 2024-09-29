package com.books.testtask.presentation.utils

import com.books.testtask.data.entities.UserEntity
import com.books.testtask.domen.entities.User
import com.books.testtask.data.entities.UserDto

fun UserEntity.toDomain(): User {
    return User(
            id = this.id,
            login = this.login,
            avatar_url = this.avatarUrl)
}

fun UserDto.toDomain(): User {
    return User(
            id = this.id,
            login = this.login,
            avatar_url = this.avatar_url)
}

fun User.toEntity(): UserEntity {
    return UserEntity(
            id = this.id,
            login = this.login,
            avatarUrl = this.avatar_url.toString()
    )
}