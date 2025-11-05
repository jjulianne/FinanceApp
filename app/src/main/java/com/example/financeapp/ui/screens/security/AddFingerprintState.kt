package com.example.financeapp.ui.screens.security

data class AddFingerprintUiState(
    val isLoading: Boolean = false,    // Para deshabilitar el boton mientras "escanea"
    val showSuccess: Boolean = false   // Para mostrar el LoadingScreen de exito
    // val errorMessage: String? = null // Podriamos aniadir esto para futuros errores
)

sealed class AddFingerprintEvent {
    object NavigateBack : AddFingerprintEvent() // Comando para volver a la pantalla anterior
}