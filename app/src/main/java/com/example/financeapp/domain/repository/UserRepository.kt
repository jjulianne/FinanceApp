package com.example.financeapp.domain.repository

import com.example.financeapp.domain.model.User

/**
 * Interfaz para el repositorio que maneja los datos del perfil de usuario.
 * Abstrae la fuente de datos (red, base de datos local, etc.) del ViewModel.
 */
interface UserRepository {
    /**
     * Obtiene el perfil del usuario desde la fuente de datos.
     * Lanza una excepcion si la obtencion falla.
     */
    suspend fun getUserProfile(): User
}