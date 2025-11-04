package com.example.financeapp.ui.screens.help

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.ChatRepository
import com.example.financeapp.ui.screens.help.OnlineSupportUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


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

    private val _events = MutableSharedFlow<OnlineSupportEvent>()
    val events = _events.asSharedFlow()
    /**
     * Llamado al presionar "Start Another Chat".
     */
    fun onStartAnotherChatClicked() {
        // 3. ¡Ahora sí hace algo! Emite el evento.
        viewModelScope.launch {
            _events.emit(OnlineSupportEvent.NavigateToNewChat)
        }
    }
}