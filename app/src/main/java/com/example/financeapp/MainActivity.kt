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
import com.example.financeapp.ui.screens.LaunchScreen
import com.example.financeapp.ui.screens.SplashScreen
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

    NavHost(
        navController = navController,
        startDestination = "splash_screen" // Cambiado a splash como pantalla inicial
    ) {
        // Pantalla Splash (carga inicial)
        composable(route = "splash_screen") {
            SplashScreen(
                onNavigateToNext = {
                    navController.navigate("launch_screen") {
                        // Eliminar splash del back stack para que no se pueda volver
                        popUpTo("splash_screen") { inclusive = true }
                    }
                }
            )
        }


        // Pantalla Launch (Login/Sign Up)
        composable(route = "launch_screen") {
            LaunchScreen(
                onNavigateToLogin = {
                    navController.navigate("login_screen")
                },
                onNavigateToSignUp = {
                    navController.navigate("signup_screen")
                },
                onNavigateToForgotPassword = {
                    navController.navigate("forgot_password_screen")
                }
            )
        }

        // Pantalla Home (principal)
        composable(route = "home_screen") {
            HomeScreen(navController = navController)
        }

        // Pantalla de detalle
        composable(route = "detail_screen") {
            DetailScreen(navController = navController)
        }
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