package com.example.financeapp.data.repository

import com.example.financeapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    // Aca iria UserSessionDao cuando este implementado
    // private val userSessionDao: UserSessionDao
) : AuthRepository {

    // Falta implementar con Room - Por ahora retorna false para forzar onboarding
    override suspend fun isOnboarded(): Boolean {
        // return userSessionDao.getUserSession()?.isOnboarded ?: false
        return false
    }

    // Falta implementar con Room - Por ahora retorna false (usuario no logueado)
    override suspend fun isUserLoggedIn(): Boolean {
        // return userSessionDao.getUserSession()?.isLoggedIn ?: false
        return false
    }
}