package com.example.financeapp.data.repository.network.retrofit

import com.example.financeapp.data.repository.network.dto.UserNetworkDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApiService {

    @POST("auth/login")
    suspend fun loginUser(
        @Header("x-api-key") apiKey: String = "123456789",
        @Body credentials: LoginRequest
    ): retrofit2.Response<LoginResponse>

    @POST("auth/create")
    suspend fun createUser(
        @Header("x-api-key") apiKey: String = "123456789",
        @Body user: CreateUserRequest
    ): retrofit2.Response<CreateUserResponse>



    @GET("users/12345")
    suspend fun getUserProfile(
        @Header("x-api-key") apiKey: String = "123456789"
    ): retrofit2.Response<UserNetworkDto>
}

