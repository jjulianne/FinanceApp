package com.example.financeapp.domain.repository

import androidx.annotation.DrawableRes
import kotlinx.coroutines.flow.Flow

// Data class para un solo chat
data class Chat(
    @DrawableRes val icon: Int,
    val title: String,
    val lastMessage: String,
    val timestamp: String,
    val isActive: Boolean
)

// Contenedor para ambas listas de chats
data class ChatData(
    val activeChats: List<Chat>,
    val endedChats: List<Chat>
)

// Interfaz del Repositorio
interface ChatRepository {
    fun getChats(): Flow<ChatData>
}