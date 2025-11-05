package com.example.financeapp.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.financeApp.R
import com.example.financeapp.ui.screens.notifications.components.NotificationDivider
import com.example.financeapp.ui.screens.notifications.components.NotificationGroupHeader
import com.example.financeapp.ui.screens.notifications.components.NotificationItem
import com.example.financeapp.ui.screens.notifications.components.NotificationListItem
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun NotificationsScreen(
    navController: NavController,
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    //datos en el viewModel
    val notifications by viewModel.notifications.collectAsState()
    val lightBackground = colorResource(id = R.color.honeydew)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.caribbean_green)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .align(Alignment.BottomCenter)
                    .clip(
                        RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 50.dp
                        )
                    )
                    .background(lightBackground)
            ) {


                // Lista de Notificaciones
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(lightBackground)
                ) {
                    items(notifications, key = {
                        // En el viewModel, cada ítem es un NotificationListItem y define si es header o item
                        when (it) {
                            is NotificationListItem.Header -> it.date // Usa la fecha como clave para encabezados
                            is NotificationListItem.Item -> it.notification.id // Usa el ID de la notificación como clave para ítems
                        }
                    }) { listItem ->
                        when (listItem) {
                            // Si es un encabezado de fecha ("Today", "Yesterday")
                            is NotificationListItem.Header -> {
                                NotificationGroupHeader(date = listItem.date) // Muestra el encabezado
                            }
                            // Si es un ítem de notificación
                            is NotificationListItem.Item -> {
                                NotificationItem(notification = listItem.notification) // Muestra la notificación

                                // Añadir divisor después de cada ítem
                                if (notifications.indexOf(listItem) < notifications.lastIndex) {
                                    NotificationDivider()
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
@Preview(
    showBackground = true,
    name = "Notifications List Preview",
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun NotificationsScreenPreview() {
    val navController = rememberNavController()
    FinanceAppTheme {
        NotificationsScreen(navController = navController)
    }
}