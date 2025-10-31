package com.example.financeapp.domain.use_case.auth

import com.example.financeapp.domain.repository.AuthRepository
import javax.inject.Inject

// Este Use Case define el estado de inicio (logueado, no logueado, necesita onboarding).
class CheckAuthState @Inject constructor(
    private val authRepository: AuthRepository
) {
    // Define posibles estados iniciales
    sealed class AuthState {
        object Authenticated : AuthState()
        object Unauthenticated : AuthState()
        object OnboardingRequired : AuthState()
    }

    suspend operator fun invoke(): AuthState {
        // Primero verifica si el usuario ya vio el onboarding (se deberia guardar en un DataStore/SharedPreferences)
        // Segundo verifica si hay un token de sesion valido (llamando a authRepository)

        val isOnboarded = authRepository.isOnboarded()

        if (!isOnboarded) {
            return AuthState.OnboardingRequired
        }

        val isUserLoggedIn = authRepository.isUserLoggedIn()

        return if (isUserLoggedIn) {
            AuthState.Authenticated
        } else {
            AuthState.Unauthenticated
        }
    }
}