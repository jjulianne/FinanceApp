package com.example.financeapp.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.model.User
import com.example.financeapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    // Flujo de estado privado y mutable
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    // Flujo de estado publico e inmutable para la UI
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        // En cuanto el ViewModel se crea, carga los datos del perfil
        loadUserProfile()
    }
/*
    /**
     * Simulador antiguo de  carga de datos del perfil.
     * Ahora usamos el `userRepository.getProfile()`.
     */
    fun loadUserProfile() {
        viewModelScope.launch {
            // Si quermeos simular un delay de red
            // kotlinx.coroutines.delay(1000)

            // Objeto "User" mockeado basado en el Postman JSON para pruebas
            val fakeUser = User(
                id = 1, // En tu UI tenías "25030024", pero el JSON dice "1". Usamos el del JSON.
                email = "john@gmail.com",
                username = "johnd",
                name = Name(firstname = "john", lastname = "doe"), // -> "John Doe"
                address = Address(
                    geolocation = Geolocation(lat = "-37.3159", long = "81.1496"),
                    city = "kilcoole",
                    street = "new road",
                    number = 7682,
                    zipcode = "12926-3874"
                ),
                phone = "1-570-236-7033"
            )

            // Success
            _uiState.update { ProfileUiState.Success(fakeUser) }

            // Error
            // _uiState.update { ProfileUiState.Error("No se pudo conectar al servidor.") }
        }
 */
    /**
     * Carga los datos del perfil desde el repositorio.
     */
    fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.update { ProfileUiState.Loading }

            try {
                // Se obtiene el usuario del repositorio
                // El repositorio ahora hace el mapeo, y esto devuelve
                // el nuevo modelo User(user_id, name, email, balance).
                val user = userRepository.getUserProfile()

                // Actualizamos el UI State con el usuario
                _uiState.update { ProfileUiState.Success(user) }

            } catch (e: Exception) {
                // Capturamos la excepcion (de red, de parseo, etc.)
                // y actualizamos el UI State con el mensaje de error.
                _uiState.update {
                    ProfileUiState.Error(
                        e.message ?: "Ocurrió un error desconocido"
                    )
                }
            }
        }
    }
}

