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
fun SignUpScreen(
    onNavigateToLogin: () -> Unit = {},
    onSignUp: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AuthScreenLayout(title = "Create Account") {

        // 👤 Nombre completo
        AppTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Full Name",
            placeholder = "example@example.com"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 📧 Email
        AppTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            placeholder = "example@example.com"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 📱 Teléfono
        AppTextField(
            value = phone,
            onValueChange = { phone = it },
            label = "Mobile Number",
            placeholder = "+123 456 789"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🎂 Fecha de nacimiento
        AppTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = "Date of Birth",
            placeholder = "DD / MM / YYYY"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🔒 Contraseña
        AppPasswordField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "••••••••"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🔒 Confirmar contraseña
        AppPasswordField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            placeholder = "••••••••"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 📜 Términos
        Text(
            text = "By continuing, you agree to\nTerms of Use and Privacy Policy.",
            color = FinWiseDarkGreen.copy(alpha = 0.8f),
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🟢 Botón principal
        AppButton(
            text = "Sign Up",
            onClick = onSignUp
        )

        Spacer(modifier = Modifier.height(30.dp))



        // 🔙 Link a Login
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account? ",
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
fun SignUpScreenPreview() {
    SignUpScreen()
}
