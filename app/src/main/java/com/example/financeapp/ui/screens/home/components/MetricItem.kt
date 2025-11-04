package com.example.financeapp.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R

@Composable
fun MetricItem(
    iconId: Int,
    title: String,
    amount: String,
    amountColor: Color // Color del monto (Recibido desde SavingsAndMetricCard)
) {


    Row(verticalAlignment = Alignment.CenterVertically) {
        // 1. Ícono
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = colorResource(id = R.color.fence_green),
            modifier = Modifier.size(35.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // 2. Títulos (Revenue Last Week / Food Last Week) y Monto
        Column {
            Text(
                text = title,
                fontSize = 12.5.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.fence_green),
                lineHeight = 12.sp
            )

            // Monto ($4.000,00 / -$100.00)
            Text(
                text = amount,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = amountColor,
                lineHeight = 15.sp
            )
        }
    }
}