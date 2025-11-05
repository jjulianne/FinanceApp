package com.example.financeapp.ui.screens.security

/**
 * Define los estados de la UI para la pantalla de Fingerprint.
 */
sealed interface FingerprintUiState {
    /** La pantalla está cargando la lista de huellas. */
    object Loading : FingerprintUiState

    /**
     * La carga fue exitosa.
     * @param fingerprints La lista de nombres de las huellas guardadas.
     */
    data class Success(val fingerprints: List<String>) : FingerprintUiState

    /** Ocurrio un error al cargar. */
    data class Error(val message: String) : FingerprintUiState
}