package com.example.financeapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    // Lee el estado del tema (Flow para que reaccione a cambios)
    fun getThemeSetting(): Flow<Boolean>

    // Guarda el estado del tema
    suspend fun saveThemeSetting(isDark: Boolean)
}