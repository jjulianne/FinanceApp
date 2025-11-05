package com.example.financeapp.data.repository

import com.example.financeapp.data.repository.mapper.toDomain
import com.example.financeapp.data.repository.network.retrofit.AuthApiService
import com.example.financeapp.domain.model.User
import com.example.financeapp.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementacion de UserRepository.
 * Esta clase es la "fuente de verdad" para los datos del perfil de usuario
 * obtenidos desde la red.
 */
@Singleton // Lo marcamos como Singleton para que Hilt maneje una sola instancia
class UserRepositoryImpl @Inject constructor(
    // Inyectamos el servicio de API de Retrofit
    private val authApiService: AuthApiService
) : UserRepository {

    override suspend fun getUserProfile(): User {
        // Hacemos la llamada a la API
        val response = authApiService.getUserProfile()

        // Verificamos si la respuesta fue exitosa y tiene un cuerpo
        if (response.isSuccessful && response.body() != null) {
            // Obtenemos el DTO "sucio" de la red (UserNetworkDto)
            val userDto = response.body()!!

            // Usamos el mapper para convertirlo a User (limpio) y retornarlo
            return userDto.toDomain()
        } else {
            // Si algo salio mal (error de red, 404, 500, etc.), lanzamos una excepcion
            // que el ViewModel podra atrapar.
            val errorMsg = response.message().takeIf { it.isNotEmpty() } ?: "Error al obtener el perfil."
            throw Exception(errorMsg)
        }
    }
}