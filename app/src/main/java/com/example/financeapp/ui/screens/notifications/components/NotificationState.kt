package com.example.financeapp.ui.screens.notifications.components
import com.example.financeapp.domain.model.NotificationItem

// Define la estructura de elementos que se van a mostrar en el LazyColumn.
// Agrupa notificaciones segun el encabezado de fecha

sealed class NotificationListItem {
    data class Header(val date: String) : NotificationListItem()
    data class Item(val notification: NotificationItem) : NotificationListItem()
}