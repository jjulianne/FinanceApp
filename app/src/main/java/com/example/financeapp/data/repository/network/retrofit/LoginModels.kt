package com.example.financeapp.data.repository.network.retrofit



// Datos que se envían en el login (por ejemplo al presionar "Sign In")
data class LoginRequest(
    val email: String,
    val password: String
)

// Datos que devuelve el backend/mockapi al loguear correctamente
data class LoginResponse(
    val token: String,
)

data class CreateUserRequest(
    val id: Int,
    val username: String,
    val email: String,
    val password: String
)

data class CreateUserResponse(
    val id: Int,
    val email: String,
    val username: String
)

