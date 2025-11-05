
package com.example.financeapp.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.example.financeapp.ui.theme.FinWiseGreen
import kotlinx.coroutines.delay

@Composable
fun PasswordChangedSuccessScreen(
    onNavigateToLogin: () -> Unit = {}
) {
    // ⏱️ Espera 2 segundos y redirige automáticamente
    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToLogin()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FinWiseGreen),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🔵 Icono circular
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.check_progress),
                    contentDescription = "Success"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Texto
            Text(
                text = "Password Has Been\nChanged Successfully",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordChangedSuccessScreenPreview() {
    PasswordChangedSuccessScreen()
}
