package com.example.financeapp.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// import com.example.financeapp.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationSettingsViewModel @Inject constructor(
    // private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationSettingsUiState())
    val uiState: StateFlow<NotificationSettingsUiState> = _uiState.asStateFlow()

    init {
        loadAllSettings()
    }

    /**
     * Carga todos los ajustes de notificación desde el repositorio.
     */
    private fun loadAllSettings() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // Carga simulada para preubas
            // val general = settingsRepository.getGeneralNotification()
            // val sound = settingsRepository.getSound()
            // ... etc.

            _uiState.update {
                it.copy(
                    isLoading = false,
                    // generalNotification = general,
                    // sound = sound,
                    // ... etc.

                    generalNotification = true,
                    sound = true,
                    soundCall = true,
                    vibrate = true,
                    transactionUpdate = false,
                    expenseReminder = false,
                    budgetNotifications = false,
                    lowBalanceAlerts = false
                )
            }
        }
    }

    // La UI llama a estas funciones, que actualizan el estado
    // y guardan el cambio en el repositorio.

    fun onGeneralNotificationChanged(checked: Boolean) {
        _uiState.update { it.copy(generalNotification = checked) }
        viewModelScope.launch {
            // settingsRepository.setGeneralNotification(checked)
        }
    }

    fun onSoundChanged(checked: Boolean) {
        _uiState.update { it.copy(sound = checked) }
        viewModelScope.launch {
            // settingsRepository.setSound(checked)
        }
    }

    fun onSoundCallChanged(checked: Boolean) {
        _uiState.update { it.copy(soundCall = checked) }
        viewModelScope.launch {
            // settingsRepository.setSoundCall(checked)
        }
    }

    fun onVibrateChanged(checked: Boolean) {
        _uiState.update { it.copy(vibrate = checked) }
        viewModelScope.launch {
            // settingsRepository.setVibrate(checked)
        }
    }

    fun onTransactionUpdateChanged(checked: Boolean) {
        _uiState.update { it.copy(transactionUpdate = checked) }
        viewModelScope.launch {
            // settingsRepository.setTransactionUpdate(checked)
        }
    }

    fun onExpenseReminderChanged(checked: Boolean) {
        _uiState.update { it.copy(expenseReminder = checked) }
        viewModelScope.launch {
            // settingsRepository.setExpenseReminder(checked)
        }
    }

    fun onBudgetNotificationsChanged(checked: Boolean) {
        _uiState.update { it.copy(budgetNotifications = checked) }
        viewModelScope.launch {
            // settingsRepository.setBudgetNotifications(checked)
        }
    }

    fun onLowBalanceAlertsChanged(checked: Boolean) {
        _uiState.update { it.copy(lowBalanceAlerts = checked) }
        viewModelScope.launch {
            // settingsRepository.setLowBalanceAlerts(checked)
        }
    }
}