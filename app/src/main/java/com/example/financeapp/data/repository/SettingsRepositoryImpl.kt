package com.example.financeapp.data.repository

import com.example.financeapp.data.datastore.SettingsDataStore
import com.example.financeapp.domain.repository.SettingsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override fun getThemeSetting(): Flow<Boolean> {
        return settingsDataStore.getThemeSetting
    }

    override suspend fun saveThemeSetting(isDark: Boolean) {
        settingsDataStore.saveThemeSetting(isDark)
    }

    /**
     * PRUEBAS: Comprueba si el pin es "1234"
     */
    override suspend fun checkPin(pin: String): Boolean {
        // Simulamos la espera de leer Room
        delay(1000L)

        // Pruebas: el pin actual correcto es "1234"
        return pin == "1234"
    }

    /**
     * PRUEBAS: Fingimos que guarda el nuevo PIN
     */
    override suspend fun savePin(pin: String) {
        // Simula la espera de escribir Room
        delay(1500L)

        println("PIN guardado (simulación): $pin")
    }

    /**
     * PRUEBAS: Fingimos que guarda la preferencia de biometría en DataStore.
     */
    override suspend fun enableBiometricLogin(isEnabled: Boolean) {
        // Simula la espera de escribir en DataStore
        delay(1000L)
        println("Preferencia de login con biometría guardada (simulación): $isEnabled")
    }
}