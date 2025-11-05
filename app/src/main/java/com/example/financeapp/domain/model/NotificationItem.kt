package com.example.financeapp.domain.model
import androidx.compose.ui.graphics.Color

data class NotificationItem(
    val id: String,
    val iconId: Int,
    val iconBackgroundColor: Color,
    val title: String,
    val subtitle: String,
    val transactionDetails: String? = null,
    val time: String
)