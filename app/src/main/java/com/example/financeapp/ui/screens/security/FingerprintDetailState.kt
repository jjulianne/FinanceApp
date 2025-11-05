package com.example.financeapp.ui.screens.security

/**
 * Define el estado de la UI para la pantalla de detalle de huella.
 */
data class FingerprintDetailState(
    val fingerprintName: String = "",
    val isLoading: Boolean = false,
    val showSuccess: Boolean = false
)