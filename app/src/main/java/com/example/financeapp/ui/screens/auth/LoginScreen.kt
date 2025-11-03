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
fun LoginScreen(
    onNavigateToForgotPassword: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
    onLogin: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AuthScreenLayout(title = "Welcome") {

        // 📧 Campo Username / Email
        AppTextField(
            value = email,
            onValueChange = { email = it },
            label = "Username or Email",
            placeholder = "example@email.com"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔒 Campo Password
        AppPasswordField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "••••••••"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🟢 Botón Log In
        AppButton(
            text = "Log In",
            onClick = onLogin
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔗 Forgot Password
        AppTextButton(
            text = "Forgot Password?",
            onClick = onNavigateToForgotPassword
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🟩 Botón Sign Up (versión clara)
        AppButton(
            text = "Sign Up",
            onClick = onNavigateToSignUp,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🖐️ Fingerprint Text
        Text(
            text = "Use Fingerprint To Access",
            color = FinWiseDarkGreen,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 🌐 Sección de redes sociales (nuevo componente)
        AuthSocialSection(
            text = "or sign up with",
            onFacebookClick = { /* TODO: login con Facebook */ },
            onGoogleClick = { /* TODO: login con Google */ }
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 🧾 Texto final
        Row {
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
fun LoginScreenPreview() {
    LoginScreen()
}
