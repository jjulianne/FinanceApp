package com.example.financeapp.ui.screens.help

/**
 * Modelo para un único mensaje en el chat.
 */
data class ChatMessage(
    val id: String,
    val text: String,
    val timestamp: String,
    val isFromUser: Boolean // True si es nuestro, False si es del asistente
)

/**
 * Estado de la UI para la pantalla de chat.
 */
data class SupportChatUiState(
    val selectedTab: String = "Support Assistant", // "Support Assistant" o "Help Center"
    val messages: List<ChatMessage> = emptyList(),
    val currentInputText: String = "",
    val isLoading: Boolean = false
)