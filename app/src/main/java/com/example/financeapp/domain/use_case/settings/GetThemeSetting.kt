package com.example.financeapp.domain.use_case.settings

import com.example.financeapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Caso de uso para obtener la configuracion del tema (claro/oscuro).
 * Se inyecta con Hilt.
 */
class GetThemeSetting @Inject constructor(
    private val repository: SettingsRepository
) {
    /**
     * Al invocar esta clase, se devuelve el Flow del repositorio.
     * Usamos 'operator fun invoke' para poder llamar a la clase como si fuera una funcion.
     */
    operator fun invoke(): Flow<Boolean> {
        return repository.getThemeSetting()
    }
}