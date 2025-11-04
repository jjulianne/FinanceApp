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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun NotificationSettingsScreen(
    viewModel: NotificationSettingsViewModel = hiltViewModel(),
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()


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
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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

            if (!state.isLoading) {
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
                        checked = state.generalNotification,
                        onCheckedChange = viewModel::onGeneralNotificationChanged
                    )

                    // Sound
                    NotificationToggleRow(
                        label = "Sound",
                        checked = state.sound,
                        onCheckedChange = viewModel::onSoundChanged
                    )

                    // Sound Call
                    NotificationToggleRow(
                        label = "Sound Call",
                        checked = state.soundCall,
                        onCheckedChange = viewModel::onSoundCallChanged
                    )

                    // Vibrate
                    NotificationToggleRow(
                        label = "Vibrate",
                        checked = state.vibrate,
                        onCheckedChange = viewModel::onVibrateChanged
                    )

                    // Transaction Update
                    NotificationToggleRow(
                        label = "Transaction Update",
                        checked = state.transactionUpdate,
                        onCheckedChange = viewModel::onTransactionUpdateChanged
                    )

                    // Expense Reminder
                    NotificationToggleRow(
                        label = "Expense Reminder",
                        checked = state.expenseReminder,
                        onCheckedChange = viewModel::onExpenseReminderChanged
                    )

                    // Budget Notifications
                    NotificationToggleRow(
                        label = "Budget Notifications",
                        checked = state.budgetNotifications,
                        onCheckedChange = viewModel::onBudgetNotificationsChanged
                    )

                    // Low Balance Alerts
                    NotificationToggleRow(
                        label = "Low Balance Alerts",
                        checked = state.lowBalanceAlerts,
                        onCheckedChange = viewModel::onLowBalanceAlertsChanged
                    )
                }
            } else {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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