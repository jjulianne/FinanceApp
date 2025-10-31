package com.example.financeapp.domain.repository

interface AuthRepository {

    // Metodo para verificar si el usuario ha completado el Onboarding
    suspend fun isOnboarded(): Boolean

    // Metodo para verificar si hay una sesion de usuario activa (token valido, etc.)
    suspend fun isUserLoggedIn(): Boolean

    // Aca iria la logica del login
}