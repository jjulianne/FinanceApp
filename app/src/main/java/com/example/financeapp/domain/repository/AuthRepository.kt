package com.example.financeapp.domain.repository

interface AuthRepository {

    // Metodo para verificar si el usuario ha completado el Onboarding
    suspend fun isWelcomed(): Boolean

    // Metodo para verificar si hay una sesion de usuario activa (token valido, etc.)
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
}