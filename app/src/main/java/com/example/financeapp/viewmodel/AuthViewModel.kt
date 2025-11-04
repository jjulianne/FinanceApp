package com.example.financeapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // Estados de la UI
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /**
     * 🔐 Inicia sesión con email y password.
     * Llama al AuthRepository (Retrofit + Room).
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                val user = authRepository.login(email, password)

                Log.d("AUTH_VIEWMODEL", "Usuario logueado: $email")


                // Si llegó hasta acá, el login fue exitoso
                _isLoggedIn.value = true
                _isLoading.value = false

            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = e.message ?: "Error al iniciar sesión"
            }
        }
    }


    fun createUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                val user = authRepository.createUser(username, email, password)

                _isLoggedIn.value = true
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }


    /**
     * Cierra sesión (opcional)
     */
    fun logout() {
        viewModelScope.launch {
            authRepository.logout() // 🔹 borra la sesión en Room
            _isLoggedIn.value = false
        }
    }

}
