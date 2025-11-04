package com.example.financeapp.ui.screens.settings

/**
 * Define el estado de la UI para la pantalla de ajustes de notificación.
 */
data class NotificationSettingsUiState(
    val generalNotification: Boolean = true,
    val sound: Boolean = true,
    val soundCall: Boolean = true,
    val vibrate: Boolean = true,
    val transactionUpdate: Boolean = false,
    val expenseReminder: Boolean = false,
    val budgetNotifications: Boolean = false,
    val lowBalanceAlerts: Boolean = false,

    val isLoading: Boolean = false
)