package com.example.financeapp.ui.screens.profile.edit_profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<EditProfileEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadInitialData()
    }
/*
    /**
     * Carga los datos iniciales "mockeados" del usuario.
     */
    private fun loadInitialData() {
        viewModelScope.launch {
            // Seteamos estado de carga
            _uiState.update { it.copy(isLoading = true) }

            // Pequenio delay simulado de red
            delay(500)

            // Usamos un fakeUser pero con los mismos datos "mock" del JSON de Postman
            val fakeUser = User(
                id = 1,
                email = "john@gmail.com",
                username = "johnd",
                name = Name(firstname = "john", lastname = "doe"),
                address = Address(
                    geolocation = Geolocation(lat = "-37.3159", long = "81.1496"),
                    city = "kilcoole",
                    street = "new road",
                    number = 7682,
                    zipcode = "12926-3874"
                ),
                phone = "1-570-236-7033"
            )

            // Actualizamos el estado con los datos cargados
            _uiState.update {
                it.copy(
                    isLoading = false,
                    userId = fakeUser.id,
                    originalUsername = fakeUser.fullName,
                    // Rellenamos los campos del formulario
                    formUsername = fakeUser.fullName,
                    formPhone = fakeUser.phone,
                    formEmail = fakeUser.email,
                    formPushNotifications = true // Valor por defecto
                )
            }
        }
    }
 */

    /**
     * Carga los datos iniciales del usuario desde el repositorio.
     */
    private fun loadInitialData() {
        viewModelScope.launch {
            // Seteamos estado de carga y limpiamos errores
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                // 3. OBTENEMOS EL USUARIO REAL (limpio)
                val user = userRepository.getUserProfile()

                // 4. Actualizamos el estado con los datos cargados
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        // Usamos los campos del 'user' limpio
                        userId = user.user_id.toIntOrNull() ?: 0,
                        originalUsername = user.name,
                        // Rellenamos los campos del formulario
                        formUsername = user.name,
                        formPhone = user.phone,
                        formEmail = user.email,
                        formPushNotifications = true // Valor por defecto
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "No se pudo cargar el perfil"
                    )
                }
            }
        }
    }

    /**
     * Simula el guardado de los datos del perfil.
     */
    fun updateProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }

            delay(1500)

            // (Aca deberiamos meterla llamada real:
            // val currentState = _uiState.value
            // userRepository.updateUser(
            //     username = currentState.formUsername,
            //     phone = currentState.formPhone,
            //     ...
            // ))

            _uiState.update { it.copy(isSaving = false) }

            _eventFlow.emit(EditProfileEvent.ProfileSaved)
        }
    }


    fun onUsernameChanged(newUsername: String) {
        _uiState.update { it.copy(formUsername = newUsername) }
    }

    fun onPhoneChanged(newPhone: String) {
        _uiState.update { it.copy(formPhone = newPhone) }
    }

    fun onEmailChanged(newEmail: String) {
        _uiState.update { it.copy(formEmail = newEmail) }
    }

    fun onPushNotificationsChanged(isEnabled: Boolean) {
        _uiState.update { it.copy(formPushNotifications = isEnabled) }
    }
}