package com.example.financeapp.ui.screens.security

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.FingerprintRepository
import com.example.financeapp.ui.screens.security.FingerprintDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FingerprintDetailViewModel @Inject constructor(
    private val fingerprintRepository: FingerprintRepository, // Hilt inyecta esto
    savedStateHandle: SavedStateHandle // Para leer los args de navegacion
) : ViewModel() {

    // Obtenemos el nombre del argumento de navegacion
    private val fingerprintName: String = checkNotNull(savedStateHandle["fingerprintName"])

    // Pasamos el Estado a la UI
    private val _uiState = MutableStateFlow(FingerprintDetailState(fingerprintName = fingerprintName))
    val uiState: StateFlow<FingerprintDetailState> = _uiState

    // Pasamos Eventos de navegacion (para que la UI reaccione)
    private val _navigationEvents = MutableSharedFlow<Unit>()
    val navigationEvents = _navigationEvents.asSharedFlow()


    // La UI llama a esta funcion
    fun onDeleteClicked() {
        if (_uiState.value.isLoading) return // Evitar doble click

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            // Llama al repositorio (que Hilt trae)
            fingerprintRepository.deleteFingerprint(fingerprintName)

            // Actualiza el estado para mostrar el exito
            // (El isLoading = false es implícito si showSuccess = true)
            _uiState.value = _uiState.value.copy(isLoading = false, showSuccess = true)
        }
    }

    // La UI llama a esto cuando el dialogo de exito se cierra
    fun onSuccessDialogDismissed() {
        _uiState.value = _uiState.value.copy(showSuccess = false)
        viewModelScope.launch {
            // Emite un evento para que la UI navegue hacia atras
            _navigationEvents.emit(Unit)
        }
    }
}