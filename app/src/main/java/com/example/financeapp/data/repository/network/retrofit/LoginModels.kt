package com.example.financeapp.data.repository.network.retrofit



// Datos que se envían en el login (por ejemplo al presionar "Sign In")
data class LoginRequest(
    val email: String,
    val password: String
)

// Datos que devuelve el backend/mockapi al loguear correctamente
data class LoginResponse(
    val userId: String,
    val name: String,
    val email: String,
    val token: String? = null // opcional
)
