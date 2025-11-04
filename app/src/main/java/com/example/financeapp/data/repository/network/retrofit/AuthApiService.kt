package com.example.financeapp.data.repository.network.retrofit

import com.example.financeapp.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {

    // Endpoint simulado de login
    @POST("auth/login")
    suspend fun loginUser(
        @Header("x-api-key") apiKey: String = "123456789",
        @Body credentials: LoginRequest
    ): User

    // Endpoint simulado para obtener el perfil del usuario
    @GET("users/12345")
    suspend fun getUserProfile(
        @Header("x-api-key") apiKey: String = "123456789"
    ): User
}
