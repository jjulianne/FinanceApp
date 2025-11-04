package com.example.financeapp.ui.screens.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.financeApp.R

@Composable
fun NotificationDivider(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 24.dp)
            .background(colorResource(id = R.color.caribbean_green))
    )
}