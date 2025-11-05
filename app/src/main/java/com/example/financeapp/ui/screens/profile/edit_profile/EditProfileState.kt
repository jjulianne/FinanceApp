package com.example.financeapp.ui.screens.profile.edit_profile

/**
 * Representa el estado de la UI para la pantalla de EditProfile.
 * Usamos una sola data class para simplificar el manejo del estado.
 */
data class EditProfileUiState(
    /** Verdadero si está cargando los datos iniciales. */
    val isLoading: Boolean = true,

    /** Verdadero si está en proceso de guardar los cambios. */
    val isSaving: Boolean = false,

    /** Mensaje de error, si lo hay. */
    val errorMessage: String? = null,

    /** ID del usuario para mostrar en la cabecera. */
    val userId: Int = 0,

    /** Nombre original para mostrar en la cabecera. */
    val originalUsername: String = "",

    val formUsername: String = "",
    val formPhone: String = "",
    val formEmail: String = "",
    val formPushNotifications: Boolean = true
)

/**
 * Eventos de una sola vez que el ViewModel puede enviar a la UI
 * (ej. para navegar).
 */
sealed interface EditProfileEvent {
    /** Indica que el perfil se guardó exitosamente. */
    object ProfileSaved : EditProfileEvent
}