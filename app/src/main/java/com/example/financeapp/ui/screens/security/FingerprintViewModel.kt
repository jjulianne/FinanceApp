package com.example.financeapp.ui.screens.security

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FingerprintViewModel @Inject constructor(
    // private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FingerprintUiState>(FingerprintUiState.Loading)
    val uiState: StateFlow<FingerprintUiState> = _uiState.asStateFlow()

    init {
        loadFingerprints()
    }

    /**
     * Carga la lista de huellas guardadas.
     * (Actualmente simulamos una carga)
     */
    fun loadFingerprints() {
        viewModelScope.launch {
            _uiState.value = FingerprintUiState.Loading

            // Simular una carga de red o base de datos
            delay(500)

            // A futuro para que funcione correctamente, esto vendria de:
            // val fingerprints = settingsRepository.getFingerprints()
            val mockFingerprints = listOf("John Fingerprint")

            _uiState.value = FingerprintUiState.Success(mockFingerprints)

            // Para probar el estado de error
            // _uiState.value = FingerprintUiState.Error("Failed to load fingerprints.")
        }
    }
}