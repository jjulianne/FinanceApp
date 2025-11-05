package com.example.financeapp.ui.screens.security

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.SettingsRepository
import com.example.financeapp.ui.screens.security.AddFingerprintEvent
import com.example.financeapp.ui.screens.security.AddFingerprintUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddFingerprintViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository // Hilt inyecta el repo
) : ViewModel() {

    // Flujo de Estado: Contiene el UiState
    private val _uiState = MutableStateFlow(AddFingerprintUiState())
    val uiState: StateFlow<AddFingerprintUiState> = _uiState

    // Flujo de Eventos: Para comandos de navegación
    private val _events = MutableSharedFlow<AddFingerprintEvent>()
    val events = _events.asSharedFlow()

    /**
     * Llamado cuando el usuario presiona el botón "Use Touch Id".
     */
    fun onUseTouchIdClicked() {
        if (_uiState.value.isLoading) return // Prevenir doble clic

        viewModelScope.launch {
            // Empezamos a "escanear" (mostramos carga)
            _uiState.update { it.copy(isLoading = true) }

            // SIMULAMOS la lectura de la huella (esperamos 2 segundos)
            delay(2000L)

            // Guardamos la preferencia en el repositorio
            settingsRepository.enableBiometricLogin(true)

            // Mostramos el mensaje de éxito
            _uiState.update { it.copy(isLoading = false, showSuccess = true) }
        }
    }

    /**
     * Llamado cuando el LoadingScreen de exito termina su animacion.
     */
    fun onSuccessDialogDismissed() {
        _uiState.update { it.copy(showSuccess = false) }
        // Emitimos el evento para que la UI navegue hacia atras
        viewModelScope.launch {
            _events.emit(AddFingerprintEvent.NavigateBack)
        }
    }
}