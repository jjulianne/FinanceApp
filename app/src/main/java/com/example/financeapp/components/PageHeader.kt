package com.example.financeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.core.noRippleClickable
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun AppHeader(
    title: String,
    onNavigateBack: () -> Unit,
    onNotifications: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        // Botón de volver
        Icon(
            painter = painterResource(id = R.drawable.bring_back),
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 38.dp, top = 69.dp)
                .size(width = 19.dp, height = 16.dp)
                .noRippleClickable { onNavigateBack() },
            tint = Color.White
        )

        // Título centrado
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 64.dp, start = 60.dp, end = 60.dp),
            textAlign = TextAlign.Center,
            softWrap = true,
            maxLines = 2
        )

        // Boton de notificaciones
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 36.dp, top = 61.dp)
                .size(30.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .noRippleClickable { onNotifications() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notif_green),
                contentDescription = "Notifications",
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(name = "Header Light Mode", showBackground = true)
@Composable
fun AppHeaderLightPreview() {
    FinanceAppTheme(darkTheme = false) {
        // Se aniade un fondo para simular la pantalla
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            AppHeader(title = "Edit My Profile", onNavigateBack = { })
        }
    }
}

@Preview(name = "Header Dark Mode", showBackground = true)
@Composable
fun AppHeaderDarkPreview() {
    FinanceAppTheme(darkTheme = true) {
        // Se aniade un fondo para simular la pantalla
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            AppHeader(title = "Edit My Profile", onNavigateBack = { })
        }
    }
}