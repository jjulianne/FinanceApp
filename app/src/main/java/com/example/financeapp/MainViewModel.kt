package com.example.financeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.use_case.settings.GetThemeSetting
import com.example.financeapp.domain.use_case.settings.SaveThemeSetting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    // Hilt inyecta nuestros Casos de Uso
    getThemeSetting: GetThemeSetting,
    private val saveThemeSetting: SaveThemeSetting
) : ViewModel() {

    // Pasa el estado del tema (leído desde DataStore)
    val isDarkTheme: StateFlow<Boolean> = getThemeSetting()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false // Por defecto, es 'false' (modo claro)
        )

    // Metodo para que el Switch guarde el cambio
    fun onThemeChange(isDark: Boolean) {
        viewModelScope.launch {
            saveThemeSetting(isDark)
        }
    }
}