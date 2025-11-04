package com.example.financeapp.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_session")
data class UserSessionEntity(
    @PrimaryKey val userId: String,
    val email: String,
    val isLoggedIn: Boolean = false,
    val isWelcomed: Boolean = false
)
