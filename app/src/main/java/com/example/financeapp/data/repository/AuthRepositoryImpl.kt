package com.example.financeapp.data.repository

import android.util.Log
import com.example.financeapp.data.repository.network.retrofit.AuthApiService
import com.example.financeapp.data.repository.network.retrofit.CreateUserRequest
import com.example.financeapp.data.repository.network.retrofit.LoginRequest
import com.example.financeapp.data.repository.room.UserSessionDao
import com.example.financeapp.data.repository.room.UserSessionEntity
import com.example.financeapp.domain.model.User
import com.example.financeapp.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(

    private val api: AuthApiService,
    private val userSessionDao: UserSessionDao
) : AuthRepository {

    //  Comprueba si el usuario ya pasó el onboarding
    override suspend fun isWelcomed(): Boolean {
        return userSessionDao.getUserSession()?.isWelcomed ?: false
    }

    //  Comprueba si el usuario ya está logueado
    override suspend fun isUserLoggedIn(): Boolean {
        return userSessionDao.getUserSession()?.isLoggedIn ?: false
    }

    //  Hace login con Retrofit y guarda sesión en Room
    override suspend fun login(email: String, password: String): User {
        val credentials = LoginRequest(email, password)
        val response = api.loginUser(credentials = credentials)

        Log.d("AUTH_DEBUG", "Código: ${response.code()} - Cuerpo: ${response.body()}")

        if (response.isSuccessful && response.body() != null) {
            // Validamos que el usuario sea el del mock correcto
            if (email == "mor_2314" && password == "83r5^_") {

                val session = UserSessionEntity(
                    userId = "1",
                    email = email,
                    isLoggedIn = true,
                    isWelcomed = true
                )
                userSessionDao.insertSession(session)

                return User(
                    user_id = "1",
                    email = email,
                    balance = 0.0
                )
            } else {
                throw Exception("Credenciales inválidas")
            }
        } else {
            throw Exception("Error al iniciar sesión")
        }
    }



   override suspend fun createUser(username: String, email: String, password: String): User {
        val request = CreateUserRequest(
            id = (0..9999).random(),
            username = username,
            email = email,
            password = password
        )

        val response = api.createUser(user = request)
        Log.d("AUTH_DEBUG", "Código: ${response.code()} - Cuerpo: ${response.body()}")

        if (response.isSuccessful && response.body() != null) {
            val user = response.body()!!

            val session = UserSessionEntity(
                userId = user.id.toString(),
                email = user.email,
                isLoggedIn = true,
                isWelcomed = true
            )
            userSessionDao.insertSession(session)

            return User(
                user_id = user.id.toString(),
                email = user.email,
                balance = 0.0
            )
        } else {
            throw Exception("Error al crear usuario")
        }
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

    override suspend fun logout() {
        userSessionDao.clearSession()
    }
}


