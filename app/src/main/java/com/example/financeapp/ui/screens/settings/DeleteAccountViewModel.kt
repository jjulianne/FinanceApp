package com.example.financeapp.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// import com.example.financeapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAccountViewModel @Inject constructor(
    // private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DeleteAccountUiState())
    val uiState: StateFlow<DeleteAccountUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<DeleteAccountEvent>()
    val events = _events.asSharedFlow()

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(confirmPassword = password) }
    }

    fun onToggleVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    /**
     * Llamado por el botón principal "Yes, Delete Account".
     * Simplemente muestra el popup de confirmación.
     */
    fun onDeleteClicked() {
        if (_uiState.value.confirmPassword.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Password is required") }
            return
        }
        _uiState.update { it.copy(showConfirmDialog = true) }
    }

    /**
     * Llamado por el botón "Cancel" del popup.
     */
    fun onCancelDialog() {
        _uiState.update { it.copy(showConfirmDialog = false) }
    }

    /**
     * Llamado por el botón "Confirm" del popup.
     * Aquí ocurre la lógica de validación.
     */
    fun onConfirmDeletion() {
        val state = _uiState.value
        // Oculta el popup e inicia la carga
        _uiState.update { it.copy(showConfirmDialog = false, isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            // Aca se valida la pass
            // val isPasswordCorrect = authRepository.checkPassword(state.confirmPassword)
            val isPasswordCorrect = true // Simulado para pruebas

            if (!isPasswordCorrect) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Incorrect password") }
                return@launch
            }

            // (Aca deberia ir la llamada a authRepository.deleteAccount())
            // Como no queremos borrarla, solo mostramos éxito.

            _uiState.update { it.copy(isLoading = false, showSuccess = true) }
        }
    }

    fun onErrorDialogDismissed() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun onSuccessDialogDismissed() {
        _uiState.update { it.copy(showSuccess = false) }
        // Emitimos el evento para desloguear al usuario
        viewModelScope.launch {
            _events.emit(DeleteAccountEvent.OnAccountDeleted)
        }
    }
}