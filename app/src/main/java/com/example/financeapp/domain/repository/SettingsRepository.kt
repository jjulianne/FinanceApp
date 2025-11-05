package com.example.financeapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    // Lee el estado del tema (Flow para que reaccione a cambios)
    fun getThemeSetting(): Flow<Boolean>

    // Guarda el estado del tema
    suspend fun saveThemeSetting(isDark: Boolean)

    /**
     * Comprueba si el PIN proporcionado coincide con el guardado.
     * @return True si el PIN es correcto, False en caso contrario.
     */
    suspend fun checkPin(pin: String): Boolean

    /**
     * Guarda (o actualiza) el nuevo PIN de seguridad.
     */
    suspend fun savePin(pin: String)

    suspend fun enableBiometricLogin(isEnabled: Boolean)
}