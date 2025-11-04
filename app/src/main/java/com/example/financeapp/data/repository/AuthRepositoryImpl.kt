package com.example.financeapp.data.repository

import com.example.financeapp.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    // Aca iria UserSessionDao cuando este implementado
    // private val userSessionDao: UserSessionDao
) : AuthRepository {

    // Falta implementar con Room - Por ahora retorna false para forzar onboarding
    override suspend fun isWelcomed(): Boolean {
        // return userSessionDao.getUserSession()?.isWelcomed ?: false
        return false
    }

    // Falta implementar con Room - Por ahora retorna false (usuario no logueado)
    override suspend fun isUserLoggedIn(): Boolean {
        // return userSessionDao.getUserSession()?.isLoggedIn ?: false
        return false
    }

    /**
     * PRUEBA: Comprueba si la contraseña es "password123"
     */
    override suspend fun checkPassword(password: String): Boolean {
        delay(1000L) // Simula una llamada de red
        // Lógica de simulación: la contraseña actual correcta es "password123"
        return password == "password123"
    }

    /**
     * PRUEBA: Finge que guarda la nueva contraseña.
     */
    override suspend fun savePassword(password: String) {
        delay(1500L) // Simula una llamada de red
        println("Nueva contraseña guardada (simulación): $password")
    }
}