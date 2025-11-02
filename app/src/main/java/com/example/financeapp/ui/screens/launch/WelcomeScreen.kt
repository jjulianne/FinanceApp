package com.example.financeapp.ui.screens.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R

@Composable
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
    ) {
        // Icono solo (grafico con flecha)
        Image(
            painter = painterResource(id = R.drawable.ic_finwise_icon),
            contentDescription = "FinWise Icon",
            modifier = Modifier
                .offset(x = 161.dp, y = 274.dp)
                .size(width = 109.dp, height = 115.dp),
            colorFilter = ColorFilter.tint(Color(0xFF00D09E)) // Color del icono: #00D09E
        )

        // Texto "FinWise" separado
        // Debajo del icono
        Text(
            text = "FinWise",
            modifier = Modifier
                .offset(x = 113.dp, y = 405.dp) // Ajusta segun necesites
                .width(206.dp),
            fontSize = 52.sp,
            fontWeight = FontWeight.SemiBold, // Poppins SemiBold 600
            textAlign = TextAlign.Center,
            lineHeight = 57.sp,
            color = Color(0xFF00D09E), // Color del texto: #00D09E
            letterSpacing = 0.sp
        )

        // Texto de descripcion (Lorem ipsum...)
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod.",
            modifier = Modifier
                .offset(x = 60.dp, y = 475.dp) // Ajusta segun el espacio necesario
                .width(310.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal, // League Spartan Regular
            textAlign = TextAlign.Center,
            lineHeight = 14.sp,
            color = Color(0xFF2D5F5D)
        )

        // Botón "Log In"
        Button(
            onClick = onNavigateToLogin,
            modifier = Modifier
                .offset(x = 112.dp, y = 530.dp)
                .size(width = 207.dp, height = 45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00D09E)
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Log In",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2D5F5D)
            )
        }

        // Botón "Sign Up"
        Button(
            onClick = onNavigateToSignUp,
            modifier = Modifier
                .offset(x = 112.dp, y = 587.dp)
                .size(width = 207.dp, height = 45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD4F1E8) // Color mas claro
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Sign Up",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2D5F5D)
            )
        }

        // Texto "Forgot Password?"
        TextButton(
            onClick = onNavigateToForgotPassword,
            modifier = Modifier
                .offset(x = 152.dp, y = 638.dp) // Ajustado para mejor alineacion
        ) {
            Text(
                text = "Forgot Password?",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold, // League Spartan SemiBold
                textAlign = TextAlign.Center,
                lineHeight = 14.sp,
                color = Color(0xFF2D5F5D)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun LaunchScreenPreview() {
    WelcomeScreen(
        onNavigateToLogin = {},
        onNavigateToSignUp = {},
        onNavigateToForgotPassword = {}
    )
}