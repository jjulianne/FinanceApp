package com.example.financeapp.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun NotificationSettingsScreen(
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    // Estados para los toggles de notificaciones
    var generalNotification by remember { mutableStateOf(true) }
    var sound by remember { mutableStateOf(true) }
    var soundCall by remember { mutableStateOf(true) }
    var vibrate by remember { mutableStateOf(true) }
    var transactionUpdate by remember { mutableStateOf(false) }
    var expenseReminder by remember { mutableStateOf(false) }
    var budgetNotifications by remember { mutableStateOf(false) }
    var lowBalanceAlerts by remember { mutableStateOf(false) }

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
                title = "Notification Settings",
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

            // Lista de opciones de notificaciones
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 160.dp)
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // General Notification
                NotificationToggleRow(
                    label = "General Notification",
                    checked = generalNotification,
                    onCheckedChange = { generalNotification = it }
                )

                // Sound
                NotificationToggleRow(
                    label = "Sound",
                    checked = sound,
                    onCheckedChange = { sound = it }
                )

                // Sound Call
                NotificationToggleRow(
                    label = "Sound Call",
                    checked = soundCall,
                    onCheckedChange = { soundCall = it }
                )

                // Vibrate
                NotificationToggleRow(
                    label = "Vibrate",
                    checked = vibrate,
                    onCheckedChange = { vibrate = it }
                )

                // Transaction Update
                NotificationToggleRow(
                    label = "Transaction Update",
                    checked = transactionUpdate,
                    onCheckedChange = { transactionUpdate = it }
                )

                // Expense Reminder
                NotificationToggleRow(
                    label = "Expense Reminder",
                    checked = expenseReminder,
                    onCheckedChange = { expenseReminder = it }
                )

                // Budget Notifications
                NotificationToggleRow(
                    label = "Budget Notifications",
                    checked = budgetNotifications,
                    onCheckedChange = { budgetNotifications = it }
                )

                // Low Balance Alerts
                NotificationToggleRow(
                    label = "Low Balance Alerts",
                    checked = lowBalanceAlerts,
                    onCheckedChange = { lowBalanceAlerts = it }
                )
            }
        }
    }
}

/**
 * Fila con toggle switch para notificaciones
 */
@Composable
private fun NotificationToggleRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            // Texto sobre la superficie (tarjeta)
            color = MaterialTheme.colorScheme.onSurface
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                // Usamos el color primario
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun NotificationSettingsScreenPreview() {
    FinanceAppTheme(darkTheme = false) {
        NotificationSettingsScreen(
            darkTheme = false
        )
    }
}