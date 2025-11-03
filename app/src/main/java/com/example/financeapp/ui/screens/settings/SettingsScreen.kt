package com.example.financeapp.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun SettingsScreen(
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToNotificationSettings: () -> Unit = {},
    onNavigateToPasswordSettings: () -> Unit = {},
    onNavigateToDeleteAccount: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                darkTheme = darkTheme,
                onNavigateToHome = onNavigateToHome,
                onNavigateToAnalysis = onNavigateToAnalysis,
                onNavigateToTransactions = onNavigateToTransactions,
                onNavigateToCategory = onNavigateToCategory,
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                // Usamos el color de fondo principal
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Header
            AppHeader(
                title = "Settings",
                onNavigateBack = onNavigateBack,
                onNotifications = { /* Falta implementar notificaciones */ }
            )

            // Tarjeta blanca de fondo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 120.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    // Usamos el color de superficie (fondo de tarjetas)
                    .background(MaterialTheme.colorScheme.surface)
            )

            // Lista de opciones de configuracion
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 160.dp)
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                // Notification Settings
                SettingsOption(
                    icon = R.drawable.notif_green,
                    label = "Notification Settings",
                    onClick = onNavigateToNotificationSettings
                )

                // Password Settings
                SettingsOption(
                    icon = R.drawable.key,
                    label = "Password Settings",
                    onClick = onNavigateToPasswordSettings
                )

                // Delete Account
                SettingsOption(
                    icon = R.drawable.profile,
                    label = "Delete Account",
                    onClick = onNavigateToDeleteAccount
                )
            }
        }
    }
}

/**
 * Componente reutilizable para cada opcion de configuracion
 */
@Composable
private fun SettingsOption(
    icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Icono circular
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }

            // Texto
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                // Texto sobre la superficie (tarjeta)
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // Flecha derecha
        Icon(
            painter = painterResource(id = R.drawable.arrow_right), // Necesitaras crear este icono
            contentDescription = "Navigate",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun SettingsScreenPreview() {
    FinanceAppTheme(darkTheme = false) {
        SettingsScreen(
            darkTheme = false
        )
    }
}