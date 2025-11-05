package com.example.financeapp.data.repository.mapper

import com.example.financeapp.data.repository.network.dto.UserNetworkDto
import com.example.financeapp.domain.model.User

/**
 * Convierte el DTO de red (UserNetworkDto) al modelo de dominio (User).
 */
fun UserNetworkDto.toDomain(): User {
    return User(
        user_id = this.id.toString(),
        name = "${this.name.firstname} ${this.name.lastname}",
        email = this.email
    )
}