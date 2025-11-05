package com.example.financeapp.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.AuthRepository
import com.example.financeapp.ui.screens.settings.PasswordSettingsEvent
import com.example.financeapp.ui.screens.settings.PasswordSettingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PasswordSettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository // Hilt inyecta el repo de Auth
) : ViewModel() {

    // Flujo de Estado: Contiene el UiState
    private val _uiState = MutableStateFlow(PasswordSettingsUiState())
    val uiState: StateFlow<PasswordSettingsUiState> = _uiState

    // Flujo de Eventos: Para comandos de navegación
    private val _events = MutableSharedFlow<PasswordSettingsEvent>()
    val events = _events.asSharedFlow()


    fun onCurrentPasswordChanged(password: String) {
        _uiState.update { it.copy(currentPassword = password) }
    }

    fun onNewPasswordChanged(password: String) {
        _uiState.update { it.copy(newPassword = password) }
    }

    fun onConfirmPasswordChanged(password: String) {
        _uiState.update { it.copy(confirmPassword = password) }
    }

    fun onToggleVisibility(field: String) {
        _uiState.update {
            when (field) {
                "current" -> it.copy(isCurrentPasswordVisible = !it.isCurrentPasswordVisible)
                "new" -> it.copy(isNewPasswordVisible = !it.isNewPasswordVisible)
                "confirm" -> it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible)
                else -> it
            }
        }
    }

    fun onErrorDialogDismissed() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun onSuccessDialogDismissed() {
        _uiState.update { it.copy(showSuccess = false) }
        viewModelScope.launch {
            _events.emit(PasswordSettingsEvent.NavigateBack) // Navega atrás
        }
    }

    fun onChangePasswordClicked() {
        val state = _uiState.value
        if (state.isLoading) return // Evitar doble clic

        // Validaciones básicas
        if (state.currentPassword.isBlank() || state.newPassword.isBlank() || state.confirmPassword.isBlank()) {
            _uiState.update { it.copy(errorMessage = "All fields are required") }
            return
        }

        if (state.newPassword != state.confirmPassword) {
            _uiState.update { it.copy(errorMessage = "New passwords do not match") }
            return
        }

        // Iniciar carga y lógica asíncrona
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            // Validamos contraseña actual
            val isCurrentPasswordCorrect = authRepository.checkPassword(state.currentPassword)
            if (!isCurrentPasswordCorrect) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Current password is incorrect") }
                return@launch
            }

            // Guardamos nueva contraseña
            authRepository.savePassword(state.newPassword)

            // Mostramos success
            _uiState.update { it.copy(isLoading = false, showSuccess = true) }
        }
    }
}