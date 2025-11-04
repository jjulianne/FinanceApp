package com.example.financeapp.ui.screens.security

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChangePinUiState(
    val currentPin: String = "",
    val newPin: String = "",
    val confirmPin: String = "",
    val isCurrentPinVisible: Boolean = false,
    val isNewPinVisible: Boolean = false,
    val isConfirmPinVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showSuccess: Boolean = false,
    val isButtonEnabled: Boolean = false
)

@HiltViewModel
class ChangePinViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository // Hilt inyecta esto
) : ViewModel() {

    // Obtenemos el estado a la UI
    private val _uiState = MutableStateFlow(ChangePinUiState())
    val uiState: StateFlow<ChangePinUiState> = _uiState

    // Pasamos los eventos de navegación
    private val _navigationEvents = MutableSharedFlow<Unit>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    // La UI llama a estas funciones)

    fun onCurrentPinChanged(pin: String) {
        if (pin.length <= 4) {
            _uiState.update {
                it.copy(
                    currentPin = pin,
                    isButtonEnabled = checkButtonEnabled(pin, it.newPin, it.confirmPin)
                )
            }
        }
    }

    fun onNewPinChanged(pin: String) {
        if (pin.length <= 4) {
            _uiState.update {
                it.copy(
                    newPin = pin,
                    isButtonEnabled = checkButtonEnabled(it.currentPin, pin, it.confirmPin)
                )
            }
        }
    }

    fun onConfirmPinChanged(pin: String) {
        if (pin.length <= 4) {
            _uiState.update {
                it.copy(
                    confirmPin = pin,
                    isButtonEnabled = checkButtonEnabled(it.currentPin, it.newPin, pin)
                )
            }
        }
    }

    fun onToggleVisibility(field: String) {
        _uiState.update {
            when (field) {
                "current" -> it.copy(isCurrentPinVisible = !it.isCurrentPinVisible)
                "new" -> it.copy(isNewPinVisible = !it.isNewPinVisible)
                "confirm" -> it.copy(isConfirmPinVisible = !it.isConfirmPinVisible)
                else -> it
            }
        }
    }

    fun onErrorDialogDismissed() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun onSuccessDialogDismissed() {
        _uiState.update { it.copy(showSuccess = false) }
        // Y le decimos a la UI que navegue
        viewModelScope.launch {
            _navigationEvents.emit(Unit)
        }
    }

    fun onChangePinClicked() {
        val state = _uiState.value

        // Empezamos a cargar
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            // Validamos que los pines coincidan
            if (state.newPin != state.confirmPin) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Pins Do Not Match") }
                return@launch
            }

            // Validamos que el PIN actual sea correcto (usando el repo)
            val isCurrentPinCorrect = settingsRepository.checkPin(state.currentPin)
            if (!isCurrentPinCorrect) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Current Pin is Incorrect") }
                return@launch
            }

            // Validamos que el pin nuevo no sea igual al viejo
            if (state.newPin == state.currentPin) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "New pin must be different") }
                return@launch
            }

            //         Faltaria implementar que lo guarde en Room
            settingsRepository.savePin(state.newPin)

            // Mostramos mensaje exitoso
            _uiState.update { it.copy(isLoading = false, showSuccess = true) }
        }
    }

    // Funcion helper interna
    private fun checkButtonEnabled(current: String, new: String, confirm: String): Boolean {
        return current.length == 4 && new.length == 4 && confirm.length == 4
    }
}