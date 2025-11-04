package com.example.financeapp.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.ui.components.AuthComponents.*
import com.example.financeapp.ui.theme.FinWiseDarkGreen
import com.example.financeapp.ui.theme.FinWiseGreen

@Composable
fun SecurityPinScreen(
    onAccept: () -> Unit = {},
    onResend: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {}
) {
    val pinDigits = listOf("2", "7", "3", "9", "1", "6")

    AuthScreenLayout(title = "Security PIN") {

        Spacer(modifier = Modifier.height(10.dp))

        // Subtítulo
        Text(
            text = "Enter Security PIN",
            color = FinWiseDarkGreen.copy(alpha = 0.8f),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 🔢 PIN DIGITS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally)
        ) {
            pinDigits.forEach { digit ->
                Box(
                    modifier = Modifier
                        .size(55.dp)
                        .background(Color.Transparent, CircleShape)
                        .border(2.dp, FinWiseGreen, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = digit,
                        color = FinWiseDarkGreen,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        // 🟢 Botón “Accept”
        AppButton(
            text = "Accept",
            onClick = onAccept
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔁 Botón “Send again”
        AppOutlinedButton(
            text = "Send again",
            onClick = onResend
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 🌐 Sección redes sociales
        AuthSocialSection(
            text = "or sign up with",
            onFacebookClick = { /* acción login Facebook */ },
            onGoogleClick = { /* acción login Google */ }
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 🔙 Link a Sign Up
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don’t have an account? ",
                color = FinWiseDarkGreen.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Text(
                text = "Sign Up",
                color = FinWiseGreen,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun SecurityPinScreenPreview() {
    SecurityPinScreen()
}

