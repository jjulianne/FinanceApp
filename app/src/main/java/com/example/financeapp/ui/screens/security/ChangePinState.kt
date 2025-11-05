package com.example.financeapp.ui.screens.security

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