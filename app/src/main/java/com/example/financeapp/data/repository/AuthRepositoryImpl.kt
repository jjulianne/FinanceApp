package com.example.financeapp.data.repository

import com.example.financeapp.data.repository.network.retrofit.AuthApiService
import com.example.financeapp.data.repository.network.retrofit.LoginRequest
import com.example.financeapp.data.repository.room.UserSessionDao
import com.example.financeapp.data.repository.room.UserSessionEntity
import com.example.financeapp.domain.model.User
import com.example.financeapp.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApiService,
    private val userSessionDao: UserSessionDao
) : AuthRepository {

    // ✅ Comprueba si el usuario ya pasó el onboarding
    override suspend fun isWelcomed(): Boolean {
        return userSessionDao.getUserSession()?.isWelcomed ?: false
    }

    // ✅ Comprueba si el usuario ya está logueado
    override suspend fun isUserLoggedIn(): Boolean {
        return userSessionDao.getUserSession()?.isLoggedIn ?: false
    }

    // ✅ Hace login con Retrofit y guarda sesión en Room
    override suspend fun login(email: String, password: String): User {
        val credentials = LoginRequest(email, password)

        // Petición a la API (simulada o real)
        val user = api.loginUser(
            apiKey = "123456789",
            credentials = credentials
        )

        // Guardar sesión local en Room
        val session = UserSessionEntity(
            userId = user.user_id,
            email = user.email,
            isLoggedIn = true,
            isWelcomed = true
        )


        userSessionDao.insertSession(session)

        return user
    }




    // 🔒 Método auxiliar opcional (para cerrar sesión)
    suspend fun logout() {
        userSessionDao.clearSession()
    }
}
