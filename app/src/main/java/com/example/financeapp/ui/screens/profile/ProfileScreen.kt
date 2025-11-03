package com.example.financeapp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.components.AppHeader
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun ProfileScreen(
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToEditProfile: () -> Unit = {},
    onNavigateToSecurity: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToHelp: () -> Unit = {},
    onLogout: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                darkTheme = darkTheme,
                onNavigateToHome = onNavigateToHome,
                onNavigateToAnalysis = onNavigateToAnalysis,
                onNavigateToTransactions = onNavigateToTransactions,
                onNavigateToCategory = onNavigateToCategory,
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) { _ ->

        // Box principal con fondo verde
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {


            // Header (TopBar)
            AppHeader(
                title = "Profile",
                onNavigateBack = onNavigateBack,
                onNotifications = { /* Falta implementar notificaciones */ }
            )


            // Tarjeta blanca de fondo (Base Shape)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 175.dp)
                    .height(757.dp) // Se extiende hasta el fondo, por debajo del NavBottom
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(MaterialTheme.colorScheme.surface)
            )

            // Foto de Perfil
            // Box contenedor para la foto
            Box(
                modifier = Modifier
                    .offset(x = 157.dp, y = 117.dp)
                    .size(117.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.2f)) // Fondo por si la imagen no carga
            ) {
                // Imagen (Lo que se ve dentro del Box)
                Image(
                    painter = painterResource(id = R.drawable.jsmith_profile),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop, // Crop para rellenar el size de la imagen
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.TopCenter) // Centra la imagen grande
                )
            }

            // Nombre de Usuario
            Text(
                text = "John Smith",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .offset(x = 157.dp, y = 252.dp)
                    .height(30.dp),
                textAlign = TextAlign.Center
            )

            // ID de Usuario
            Text(
                text = "ID: 25030024",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier
                    .offset(x = 175.dp, y = 278.dp)
                    .width(82.dp)
                    .height(20.dp),
                textAlign = TextAlign.Center
            )

            // List
            Column(
                modifier = Modifier
                    .offset(x = 38.dp, y = 350.dp)
                    .width(200.dp),
            ) {
                ProfileMenuItem(
                    icon = R.drawable.profile,
                    text = "Edit Profile",
                    backgroundColor = Color(0xFF6DB6FE),
                    onClick = onNavigateToEditProfile
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileMenuItem(
                    icon = R.drawable.security,
                    text = "Security",
                    backgroundColor = Color(0xFF3299FF),
                    onClick = onNavigateToSecurity
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileMenuItem(
                    icon = R.drawable.settings,
                    text = "Setting",
                    backgroundColor = Color(0xFF0068FF),
                    onClick = onNavigateToSettings
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileMenuItem(
                    icon = R.drawable.help_white,
                    text = "Help",
                    backgroundColor = Color(0xFF6DB6FE),
                    onClick = onNavigateToHelp
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileMenuItem(
                    icon = R.drawable.logout,
                    text = "Logout",
                    backgroundColor = Color(0xFF3299FF),
                    onClick = onLogout
                )
            }
        }
    }
}

/**
 * Componente para cada item de la lista de opciones
 * (Edit Profile, Security, etc.)
 */
@Composable
private fun ProfileMenuItem(
    icon: Int,
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono con fondo circular de color
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = text,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Texto
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun ProfileScreenPreview() {
    FinanceAppTheme(darkTheme = false) { // <-- Cambia a 'true' para probar modo oscuro
        ProfileScreen(
            darkTheme = false
            // Los otros parámetros tienen valores por defecto
        )
    }
}