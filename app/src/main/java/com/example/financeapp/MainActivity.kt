package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.financeapp.navigation.FinWiseNavigation
import com.example.financeapp.ui.theme.FinanceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Se recibe el estado del tema DESDE EL VIEWMODEL
            val isDarkTheme by mainViewModel.isDarkTheme.collectAsState()

            FinanceAppTheme(darkTheme = isDarkTheme) {

                // Pasamos el estado y la funcion de cambio
                // al NavGraph, que se los pasara a las pantallas.
                FinWiseNavigation(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = mainViewModel::onThemeChange
                )
            }
        }
    }
}