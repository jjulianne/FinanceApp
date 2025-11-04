package com.example.financeapp.ui.screens.help

import com.example.financeapp.domain.repository.Chat

/**
 * Define el estado de la UI para la pantalla de soporte online.
 */
data class OnlineSupportUiState(
    val activeChats: List<Chat> = emptyList(),
    val endedChats: List<Chat> = emptyList(),
    val isLoading: Boolean = true
)