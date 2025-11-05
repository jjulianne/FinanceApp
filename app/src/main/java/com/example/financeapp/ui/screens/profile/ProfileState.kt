package com.example.financeapp.ui.screens.profile

import com.example.financeapp.domain.model.User

/**
 * Define los posibles estados de la pantalla de Perfil.
 */
sealed interface ProfileUiState {
    /**
     * La pantalla esta cargando los datos.
     */
    data object Loading : ProfileUiState

    /**
     * Los datos se cargaron exitosamente.
     * @param user El objeto User (del domain) con los datos del perfil.
     */
    data class Success(val user: User) : ProfileUiState

    /**
     * Ocurrio un error al cargar los datos.
     * @param message El mensaje de error a mostrar.
     */
    data class Error(val message: String) : ProfileUiState
}