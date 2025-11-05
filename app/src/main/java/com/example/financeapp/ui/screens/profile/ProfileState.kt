package com.example.financeapp.ui.screens.profile


data class User(
    val id: Int,
    val email: String,
    val username: String,
    val name: Name,
    val address: Address,
    val phone: String
) {
    /**
     * Propiedad "helper" para mostrar el nombre completo
     */
    val fullName: String
        get() = "${name.firstname.replaceFirstChar { it.uppercase() }} ${name.lastname.replaceFirstChar { it.uppercase() }}"
}

data class Name(
    val firstname: String,
    val lastname: String
)

data class Address(
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    val geolocation: Geolocation
)

data class Geolocation(
    val lat: String,
    val long: String
)


/**
 * Define los diferentes estados posibles de la pantalla de perfil.
 */
sealed interface ProfileUiState {
    /** La pantalla está cargando los datos (ej. esperando respuesta de red). */
    object Loading : ProfileUiState

    /** Los datos se cargaron exitosamente y tenemos un [User]. */
    data class Success(val user: User) : ProfileUiState

    /** Ocurrió un error al cargar los datos. */
    data class Error(val message: String) : ProfileUiState
}