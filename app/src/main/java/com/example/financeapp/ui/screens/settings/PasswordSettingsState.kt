package com.example.financeapp.ui.screens.settings

/**
 * Define el estado de la UI para la pantalla de cambio de contrasenia.
 */
data class PasswordSettingsUiState(
    val currentPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val isCurrentPasswordVisible: Boolean = false,
    val isNewPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showSuccess: Boolean = false
)

/**
 * Define los eventos de una sola vez para la pantalla de cambio de contrasenia.
 */
sealed class PasswordSettingsEvent {
    object NavigateBack : PasswordSettingsEvent()
}