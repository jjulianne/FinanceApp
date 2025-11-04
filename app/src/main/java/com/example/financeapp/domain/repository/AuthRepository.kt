package com.example.financeapp.domain.repository

import com.example.financeapp.domain.model.User

interface AuthRepository {

    suspend fun isWelcomed(): Boolean
    suspend fun isUserLoggedIn(): Boolean

    // Nuevo método: login con credenciales
    suspend fun login(email: String, password: String): User

    suspend fun logout()

    suspend fun createUser(username: String, email: String, password: String): User

}
