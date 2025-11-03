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
fun NewPasswordScreen(
    onChangePassword: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AuthScreenLayout(title = "New Password") {

        Spacer(modifier = Modifier.height(12.dp))

        // 📜 Subtítulo opcional
        Text(
            text = "Please set your new password below.",
            color = FinWiseDarkGreen.copy(alpha = 0.8f),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🔒 Nueva contraseña
        AppPasswordField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = "New Password",
            placeholder = "••••••••"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔒 Confirmar nueva contraseña
        AppPasswordField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm New Password",
            placeholder = "••••••••"
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 🟢 Botón Change Password
        AppButton(
            text = "Change Password",
            onClick = onChangePassword
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 🔙 Volver al Login
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Remember your password? ",
                color = FinWiseDarkGreen.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Text(
                text = "Log In",
                color = FinWiseGreen,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onNavigateToLogin() }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun NewPasswordScreenPreview() {
    com.example.financeapp.ui.theme.FinanceAppTheme {
        NewPasswordScreen()
    }
}
