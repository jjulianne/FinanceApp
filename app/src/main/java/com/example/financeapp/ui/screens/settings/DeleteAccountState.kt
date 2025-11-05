package com.example.financeapp.ui.screens.settings

/**
 * Define el estado de la UI para la pantalla de borrado de cuenta.
 */
data class DeleteAccountUiState(
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val showConfirmDialog: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showSuccess: Boolean = false
)

/**
 * Define los eventos de una sola vez para la pantalla.
 */
sealed class DeleteAccountEvent {
    /**
     * Indica que la cuenta se "borró" y debemos navegar
     * fuera (ej. a la pantalla de Login).
     */
    object OnAccountDeleted : DeleteAccountEvent()
}