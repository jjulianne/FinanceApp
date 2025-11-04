package com.example.financeapp.ui.screens.help

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.Chat
import com.example.financeapp.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class OnlineSupportUiState(
    val activeChats: List<Chat> = emptyList(),
    val endedChats: List<Chat> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class OnlineSupportViewModel @Inject constructor(
    chatRepository: ChatRepository
) : ViewModel() {

    val uiState: StateFlow<OnlineSupportUiState> =
        chatRepository.getChats()
            .map { chatData ->
                OnlineSupportUiState(
                    activeChats = chatData.activeChats,
                    endedChats = chatData.endedChats,
                    isLoading = false
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000), // Inicia cuando la UI se suscribe
                initialValue = OnlineSupportUiState(isLoading = true) // Estado inicial de carga
            )

    /**
     * Llamado al presionar "Start Another Chat".
     * (Simulación, no hace nada por ahora).
     */
    fun onStartAnotherChatClicked() {
        // En una app real, esto podría navegar a una pantalla
        // de chat activa o mostrar un formulario de contacto.
    }
}