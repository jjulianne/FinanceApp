
package com.example.financeapp.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.ui.components.AuthComponents.*
import com.example.financeapp.ui.theme.FinWiseDarkGreen
import com.example.financeapp.ui.theme.FinWiseGreen

@Composable
fun ForgotPasswordScreen(
    onNavigateToLogin: () -> Unit = {},
    onResetPassword: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {} // ✅ agregado para el botón y links de Sign Up
) {
    var email by remember { mutableStateOf("") }

    AuthScreenLayout(title = "Forgot Password") {

        // Subtítulo
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Reset Password?",
            color = FinWiseDarkGreen,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Descripción (lorem)
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            color = FinWiseDarkGreen.copy(alpha = 0.8f),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo Email
        AppTextField(
            value = email,
            onValueChange = { email = it },
            label = "Enter email address",
            placeholder = "example@example.com"
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón principal (Next step)
        AppButton(
            text = "Next step",
            onClick = onResetPassword   // lo dejamos así por ahora; cuando conectemos backend, cambiamos
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón secundario (Sign Up)
        AppOutlinedButton(
            text = "Sign Up",
            onClick = onNavigateToSignUp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Texto “or sign up with”
        Text(
            text = "",
            color = FinWiseDarkGreen.copy(alpha = 0.6f),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de redes sociales
        AuthSocialSection()

        Spacer(modifier = Modifier.height(30.dp))

        // Texto inferior
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don’t have an account? ",
                color = FinWiseDarkGreen.copy(alpha = 0.7f),
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

        Spacer(modifier = Modifier.height(8.dp))

        // Link para volver al login (si querés dejarlo también)
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {


        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun ForgotPasswordScreenPreview() {
    com.example.financeapp.ui.theme.FinanceAppTheme {
        ForgotPasswordScreen()
    }
}
