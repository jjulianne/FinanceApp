package com.example.financeapp.ui.screens.notifications.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R

@Composable
fun NotificationGroupHeader(date: String) {
 // Es la fecha "Today", "Yesterday"...
    Text(
        text = date,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 15.dp),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = colorResource(id = R.color.fence_green)
    )
}