package com.example.financeapp.domain.use_case.settings

import com.example.financeapp.domain.repository.SettingsRepository
import javax.inject.Inject

/**
 * Caso de uso para guardar la configuración del tema (claro/oscuro).
 * Se inyecta con Hilt.
 */
class SaveThemeSetting @Inject constructor(
    private val repository: SettingsRepository
) {
    /**
     * Al invocar esta clase, se guarda el valor booleano en el repositorio.
     * Es una funcion 'suspend' porque la escritura en DataStore es asincrona.
     */
    suspend operator fun invoke(isDark: Boolean) {
        repository.saveThemeSetting(isDark)
    }
}