package com.example.financeapp.ui.screens.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.use_case.auth.CheckAuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val checkAuthState: CheckAuthState
) : ViewModel() {

    // El estado inicial es Loading, para que la Splash Screen tenga tiempo de mostrarse
    private val _authState = MutableStateFlow<CheckAuthState.AuthState?>(null)
    val authState: StateFlow<CheckAuthState.AuthState?> = _authState

    init {
        checkAuthenticationState()
    }

    private fun checkAuthenticationState() {
        viewModelScope.launch {
            // Un pequeño delay para que la SplashScreen sea visible antes de consultar
            kotlinx.coroutines.delay(1000)
            _authState.value = checkAuthState()
        }
    }
}