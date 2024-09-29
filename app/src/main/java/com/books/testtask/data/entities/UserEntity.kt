package com.books.testtask.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "login") val login: String,
        @ColumnInfo(name = "avatarUrl") val avatarUrl: String
)