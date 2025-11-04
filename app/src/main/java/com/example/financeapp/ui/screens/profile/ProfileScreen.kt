package com.example.financeapp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeApp.R
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.components.AppHeader
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
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
    val uiState by viewModel.uiState.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

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
    ) { _ -> // Antes tenia un padding el cual borre porque sino me tiraba la pantalla para abajo

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

            when (val state = uiState) {
                is ProfileUiState.Loading -> {
                    // Estado de Carga
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is ProfileUiState.Success -> {
                    // Estado Success
                    ProfileContent(
                        user = state.user, // Pasamos el usuario al contenido
                        onNavigateToEditProfile = onNavigateToEditProfile,
                        onNavigateToSecurity = onNavigateToSecurity,
                        onNavigateToSettings = onNavigateToSettings,
                        onNavigateToHelp = onNavigateToHelp,
                        onShowLogoutDialog = { showLogoutDialog = true } // Lambda para mostrar el popup
                    )
                }
                is ProfileUiState.Error -> {
                    // Estado de Error
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }


            if (showLogoutDialog) {
                LogoutConfirmationDialog(
                    onConfirm = {
                        showLogoutDialog = false
                        onLogout()
                    },
                    onCancel = {
                        showLogoutDialog = false
                    }
                )
            }
        }
    }
}

/**
 * Contenido principal de la pantalla de perfil (cuando los datos se cargaron).
 */
@Composable
private fun ProfileContent(
    user: User, // Recibe el objeto User con los datos
    onNavigateToEditProfile: () -> Unit,
    onNavigateToSecurity: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToHelp: () -> Unit,
    onShowLogoutDialog: () -> Unit
) {
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
        // Imagen
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
        text = user.fullName,
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
        text = "ID: ${user.id}",
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
            onClick = onShowLogoutDialog
        )
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


/**
 * Popup de confirmación para cerrar sesión (Logout).
 */
@Composable
private fun LogoutConfirmationDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    // Dialog proporciona el fondo oscurecido automaticamente
    Dialog(onDismissRequest = onCancel) {
        Card(
            shape = RoundedCornerShape(24.dp), // Esquinas redondeadas como en la imagen
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface // Fondo de la tarjeta
            ),
            modifier = Modifier.width(300.dp) // Ancho aproximado de la imagen
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Titulo
                Text(
                    text = "End Session",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Subtitulo
                Text(
                    text = "Are you sure you want to log out?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Boton "Yes, End Session"
                Button(
                    onClick = onConfirm,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        text = "Yes, End Session",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Boton "Cancel"
                Button(
                    onClick = onCancel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun ProfileScreenPreview() {
    FinanceAppTheme(darkTheme = false) { // Cambia a 'true' para probar modo oscuro
        ProfileScreen(
            darkTheme = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogoutConfirmationDialogPreview() {
    FinanceAppTheme(darkTheme = false) {
        LogoutConfirmationDialog(
            onConfirm = {},
            onCancel = {}
        )
    }
}