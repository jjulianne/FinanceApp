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
    // Se crea el controlador de navegacion que recordara el estado (backstack, etc.)
    val navController = rememberNavController()

    // Usamos NavHost porque es el contenedor que intercambia los composables (pantallas)
    NavHost(
        navController = navController,
        startDestination = "home_screen" // Define la pantalla de inicio
    ) {
        // Definimos la navegacion
        composable(route = "home_screen") {
            HomeScreen(navController = navController)
        }
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
                    // Accion de navegacion: ir a la pantalla de detalle
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
                    // Accion de navegacion: volver a la pantalla anterior
                    navController.popBackStack()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}