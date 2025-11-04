package com.example.financeapp.ui.screens.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.domain.model.NotificationItem

@Composable
fun NotificationItem(notification: NotificationItem, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        // 1. Ícono con fondo
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(notification.iconBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = notification.iconId),
                contentDescription = notification.title,
                tint= colorResource(R.color.fence_green),
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // 2. Contenido (Título, Subtítulo, Detalles)
        Column(modifier = Modifier.weight(1f)) {
            // Título
            Text(
                text = notification.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.fence_green),
                lineHeight = 15.sp
            )

            // Subtítulo
            Text(
                modifier = Modifier.padding(end = 56.dp),
                text = notification.subtitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.fence_green),
                lineHeight = 14.sp,
            )

            // Detalles de Transacción (Si dice que es transaction entonces aparece el texto en azul)
            if (notification.transactionDetails != null) {
                Text(
                    text = notification.transactionDetails,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.ocean_blue),
                    lineHeight = 11.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            //  3. Fecha y Hora
            Text(
                text = notification.time,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = colorResource(id = R.color.ocean_blue),
                lineHeight = 15.sp,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                textAlign = TextAlign.End,
            )
        }
    }
}