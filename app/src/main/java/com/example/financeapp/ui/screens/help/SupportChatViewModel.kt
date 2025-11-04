package com.example.financeapp.ui.screens.help

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val savedStateHandle: SavedStateHandle // Si necesitas el ID del chat
) : ViewModel() {

    private val _uiState = MutableStateFlow(SupportChatUiState())
    val uiState: StateFlow<SupportChatUiState> = _uiState.asStateFlow()

    init {
        val chatId: String? = savedStateHandle.get("chatId")

        if (chatId == null || chatId == "new") {
            createNewChat()
        } else {
            loadChatHistory(chatId)
        }
    }

    private fun createNewChat() {
        _uiState.update {
            it.copy(
                messages = emptyList(),
                // Opcional: Se podria aniadir un mensaje de bienvenida si se desea
                // messages = listOf(Message(text = "¡Bienvenidooo! ¿Cómo podemos ayudarte?", isFromUser = false, ...)),
                isLoading = false
            )
        }
    }

    /**
     * Carga los mensajes del chat (simulado).
     */
    private fun loadChatHistory(chatId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(500) // Simular carga

            val mockMessages = listOf(
                ChatMessage("1", "Welcome, I am your virtual assistant.\nHow can I help you today?", "14:00", false),
                ChatMessage("2", "Hello! I have a question. How can I record my expenses by date?", "14:01", true),
                ChatMessage("3", "Response to your request: You can register expenses in the top menu of the homepage.", "14:03", false),
                ChatMessage("4", "Enter the purchase information, including the date, etc.", "14:03", false),
                ChatMessage("5", "OK, thanks a lot.", "14:05", true),
                ChatMessage("6", "It was a pleasure to accommodate your request. See you soon!", "14:06", false)
            )

            _uiState.update {
                it.copy(isLoading = false, messages = mockMessages)
            }
        }
    }

    fun onTabSelected(tab: String) {
        _uiState.update { it.copy(selectedTab = tab) }
        // Se podria cargar otra lista de mensajes si el tab cambia algun dia
    }

    fun onInputTextChanged(text: String) {
        _uiState.update { it.copy(currentInputText = text) }
    }

    fun onSendMessage() {
        if (_uiState.value.currentInputText.isBlank()) return

        val newMessage = ChatMessage(
            id = (_uiState.value.messages.size + 1).toString(),
            text = _uiState.value.currentInputText,
            timestamp = "14:14", // Simulado
            isFromUser = true
        )

        _uiState.update {
            it.copy(
                messages = it.messages + newMessage,
                currentInputText = ""
            )
        }
    }
}