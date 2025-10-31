package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.screens.launch.OnboardingScreen
import com.example.financeapp.ui.screens.launch.SplashScreen
import com.example.financeapp.ui.theme.FinanceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceAppTheme {
                AppNavigation()
            }
        }
    }
}

/**
 * Composable principal que configura la navegacion.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Rutas de destino
    val SPLASH_ROUTE = "splash_screen"
    val ONBOARDING_ROUTE = "onboarding_screen"
    val LOGIN_ROUTE = "login_screen"
    val HOME_ROUTE = "home_screen"
    val DETAIL_ROUTE = "detail_screen"

    NavHost(
        navController = navController,
        startDestination = SPLASH_ROUTE // Cambiado a splash como pantalla inicial
    ) {
        // Pantalla Splash (carga inicial)
        composable(route = SPLASH_ROUTE) {
            // El SplashScreen maneja 3 posibles destinos basados en el estado de Auth.
            SplashScreen(
                // Navegacion a Home
                onNavigateToHome = {
                    navController.navigate(HOME_ROUTE) {
                        popUpTo(SPLASH_ROUTE) { inclusive = true }
                    }
                },
                // Navegacion a Onboarding (si es usuario nuevo)
                onNavigateToOnboarding = {
                    navController.navigate(ONBOARDING_ROUTE) {
                        popUpTo(SPLASH_ROUTE) { inclusive = true }
                    }
                },
                // Navegacion a Login (si ya hizo onboarding, pero no esta logueado)
                onNavigateToLogin = {
                    navController.navigate(LOGIN_ROUTE) {
                        popUpTo(SPLASH_ROUTE) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla Onboarding
        composable(route = ONBOARDING_ROUTE) {
            OnboardingScreen(
                onNavigateToLogin = {
                    navController.navigate(LOGIN_ROUTE)
                },
                onNavigateToSignUp = {
                    navController.navigate("signup_screen") // Falta crear esta composable
                },
                onNavigateToForgotPassword = {
                    navController.navigate("forgot_password_screen") // Falta crear esta composable
                }
            )
        }

        // Pantalla de Login (Deje por ahora la de HomeScreen para que se pueda navegar)
        composable(route = LOGIN_ROUTE) {
            HomeScreen(navController = navController)
        }


        // Pantalla Home (principal)
        composable(route = HOME_ROUTE) {
            HomeScreen(navController = navController)
        }

        // Pantalla de detalle
        composable(route = DETAIL_ROUTE) {
            DetailScreen(navController = navController)
        }

        // Aca van mas rutas
    }
}

// PANTALLAS DE EJEMPLO

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Estás en la Pantalla Principal")
            Button(
                onClick = {
                    navController.navigate("detail_screen")
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Ir a Detalles")
            }
        }
    }
}

@Composable
fun DetailScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "¡Esta es la Pantalla de Detalles!")
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}