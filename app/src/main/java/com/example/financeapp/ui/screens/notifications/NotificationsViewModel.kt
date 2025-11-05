package com.example.financeapp.ui.screens.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.ui.graphics.Color
import com.example.financeApp.R
import com.example.financeapp.domain.model.NotificationItem
import com.example.financeapp.ui.screens.notifications.components.NotificationListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor() : ViewModel() {

    private val _notifications = MutableStateFlow<List<NotificationListItem>>(emptyList())
    val notifications: StateFlow<List<NotificationListItem>> = _notifications.asStateFlow()

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            val ColorCaribbeanGreen = Color(0xFF00D09E)

            // Datos de las notificaciones individuales
            val mockNotifications = listOf(
                NotificationItem(
                    id = "1",
                    iconId = R.drawable.notif_green,
                    iconBackgroundColor = ColorCaribbeanGreen,
                    title = "Reminder!",
                    subtitle = "Set up your automatic savings to meet your savings goal...",
                    time = "17:00 - April 24"
                ),
                NotificationItem(
                    id = "2",
                    iconId = R.drawable.star,
                    iconBackgroundColor = ColorCaribbeanGreen,
                    title = "New Update",
                    subtitle = "Set up your automatic savings to meet your savings goal...",
                    time = "17:00 - April 24"
                ),
                NotificationItem(
                    id = "3",
                    iconId = R.drawable.coin,
                    iconBackgroundColor = ColorCaribbeanGreen,
                    title = "Transactions",
                    subtitle = "A new transaction has been registered",
                    transactionDetails = "Groceries | Pantry | -$100,00",
                    time = "17:00 - April 24"
                ),
                NotificationItem(
                    id = "4",
                    iconId = R.drawable.notif_green,
                    iconBackgroundColor = ColorCaribbeanGreen,
                    title = "Reminder!",
                    subtitle = "Set up your automatic savings to meet your savings goal...",
                    time = "17:00 - April 24"
                ),
                NotificationItem(
                    id = "5",
                    iconId = R.drawable.arrow,
                    iconBackgroundColor = ColorCaribbeanGreen,
                    title = "Expense Record",
                    subtitle = "We recommend that you be more attentive to your finances.",
                    time = "17:00 - April 24"
                ),
                NotificationItem(
                    id = "6",
                    iconId = R.drawable.coin,
                    iconBackgroundColor = ColorCaribbeanGreen,
                    title = "Transactions",
                    subtitle = "A new transaction has been registered",
                    transactionDetails = "Food | Dinner | -$70,40",
                    time = "17:00 - April 24"
                )
            )

            // Se agrupa por fecha. es un listado mixto de headers e items
            val groupedNotifications = mutableListOf<NotificationListItem>()
            groupedNotifications.add(NotificationListItem.Header("Today"))
            groupedNotifications.add(NotificationListItem.Item(mockNotifications[0]))
            groupedNotifications.add(NotificationListItem.Item(mockNotifications[1]))
            groupedNotifications.add(NotificationListItem.Header("Yesterday"))
            groupedNotifications.add(NotificationListItem.Item(mockNotifications[2]))
            groupedNotifications.add(NotificationListItem.Item(mockNotifications[3]))
            groupedNotifications.add(NotificationListItem.Header("This Weekend"))
            groupedNotifications.add(NotificationListItem.Item(mockNotifications[4]))
            groupedNotifications.add(NotificationListItem.Item(mockNotifications[5]))


            _notifications.value = groupedNotifications // Actualiza el StateFlow con la lista agrupada
        }
    }
}