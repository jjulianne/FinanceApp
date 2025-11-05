package com.example.financeapp.domain.repository

import com.example.financeapp.domain.model.User

interface AuthRepository {

    suspend fun isWelcomed(): Boolean
    suspend fun isUserLoggedIn(): Boolean

    // Aca iria la logica del login
    /**
     * Comprueba si la contraseña actual proporcionada es correcta.
     */
    suspend fun checkPassword(password: String): Boolean

    /**
     * Guarda la nueva contraseña del usuario.
     */
    suspend fun savePassword(password: String)
    suspend fun login(email: String, password: String): User

    suspend fun logout()

    suspend fun createUser(username: String, email: String, password: String): User

}