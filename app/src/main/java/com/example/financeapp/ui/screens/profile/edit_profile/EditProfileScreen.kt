package com.example.financeapp.ui.screens.profile.edit_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeApp.R
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is EditProfileEvent.ProfileSaved -> {
                    onNavigateBack()
                }
            }
        }
    }


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
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                // Usamos el color de fondo principal
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Header
            AppHeader(
                title = "Edit My Profile",
                onNavigateBack = onNavigateBack,
                onNotifications = { /* Falta implementar notificaciones */ }
            )

            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                // Mostramos el contenido solo cuando la carga termino
                EditProfileContent(
                    uiState = uiState,
                    onUsernameChanged = viewModel::onUsernameChanged,
                    onPhoneChanged = viewModel::onPhoneChanged,
                    onEmailChanged = viewModel::onEmailChanged,
                    onPushNotificationsChanged = viewModel::onPushNotificationsChanged,
                    onUpdateProfileClick = viewModel::updateProfile,
                    darkTheme = darkTheme,
                    onThemeChange = onThemeChange
                )
            }
        }
    }
}

/**
 * Composable privado para el contenido principal (formulario)
 */
@Composable
private fun EditProfileContent(
    uiState: EditProfileUiState,
    onUsernameChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPushNotificationsChanged: (Boolean) -> Unit,
    onUpdateProfileClick: () -> Unit,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    // Tarjeta blanca de fondo
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 175.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            // Usamos el color de superficie (fondo de tarjetas)
            .background(MaterialTheme.colorScheme.surface)
    )

    // Foto de perfil con icono de editar
    Box(
        modifier = Modifier
            .offset(x = 157.dp, y = 117.dp)
            .size(117.dp)
    ) {
        // Foto de perfil
        Box(
            modifier = Modifier
                .size(117.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.2f))
        ) {
            Image(
                painter = painterResource(id = R.drawable.jsmith_profile),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.TopCenter)
            )
        }

        // Icono de camara para editar foto
        Box(
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-5).dp, y = (-5).dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    // Falta implementar galeria para cambio de foto
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera_green),
                contentDescription = "Edit photo",
                modifier = Modifier.size(16.dp)
            )
        }
    }

    // Nombre de Usuario
    Text(
        text = uiState.originalUsername,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        // Texto sobre la superficie (tarjeta)
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .offset(x = 157.dp, y = 252.dp)
            .height(30.dp),
        textAlign = TextAlign.Center
    )

    // ID de Usuario
    Text(
        text = "ID: ${uiState.userId}",
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        // Texto sobre la superficie (tarjeta), con alfa
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        modifier = Modifier
            .offset(x = 175.dp, y = 278.dp)
            .width(82.dp)
            .height(20.dp),
        textAlign = TextAlign.Center
    )

    // Formulario de configuracion
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 315.dp)
            .padding(horizontal = 38.dp)
    ) {
        // Titulo
        Text(
            text = "Account Settings",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            // Texto sobre la superficie (tarjeta)
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Username
        SettingsTextField(
            label = "Username",
            value = uiState.formUsername,
            onValueChange = onUsernameChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Phone
        SettingsTextField(
            label = "Phone",
            value = uiState.formPhone,
            onValueChange = onPhoneChanged
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Email Address
        SettingsTextField(
            label = "Email Address",
            value = uiState.formEmail,
            onValueChange = onEmailChanged
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Notifications Toggle
        SettingsToggleRow(
            label = "Push Notifications",
            checked = uiState.formPushNotifications,
            onCheckedChange = onPushNotificationsChanged
        )

        Spacer(modifier = Modifier.height(12.dp))


        SettingsToggleRow(
            label = "Turn Dark Theme",
            checked = darkTheme,
            onCheckedChange = onThemeChange // Llama a la funciOn del ViewModel
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Update Profile
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onUpdateProfileClick,
                enabled = !uiState.isSaving,
                modifier = Modifier
                    .width(169.dp)
                    .height(36.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(25.dp)
            ) {
                // Muestra spinner si está guardando
                if (uiState.isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Update Profile",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}


/**
 * Campo de texto personalizado para configuraciones
 */
@Composable
private fun SettingsTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 15.sp, // ajuste la font como el figma porque asi se veia toda la pantalla
            fontWeight = FontWeight.Medium,
            // Texto sobre la superficie (tarjeta)
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        val interactionSource = remember { MutableInteractionSource() }

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(
                    // Fondo del textfield (variante de superficie)
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 13.sp,
                // Texto sobre el fondo del textfield
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp), // Padding horizontal
                    contentAlignment = Alignment.CenterStart // Centra el texto verticalmente
                ) {
                    innerTextField()
                }
            }
        )
    }
}

/**
 * Fila con toggle switch para configuraciones
 */
@Composable
private fun SettingsToggleRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp, // ajuste la font como el figma porque asi se veia toda la pantalla
            fontWeight = FontWeight.Medium,
            // Texto sobre la superficie (tarjeta)
            color = MaterialTheme.colorScheme.onSurface
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                // Usamos el color primario
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun EditProfileScreenPreview() {
    // Envolvemos el Preview para poder probar ambos modos
    FinanceAppTheme(darkTheme = false) { // Si queres ver el modo oscuro ponele 'true'
        EditProfileScreen(
            darkTheme = false,
            onThemeChange = { /* No hace nada en el preview */ }
        )
    }
}

@Preview(showBackground = true, name = "Default State", widthDp = 430, heightDp = 932)
@Composable
fun EditProfileScreenPreviewSinViewModel() {
    FinanceAppTheme(darkTheme = false) { // Si queres ver el modo oscuro ponele 'true'

        // Creamos un estado de prueba para el preview sin viewmodel
        val fakeSuccessState = EditProfileUiState(
            isLoading = false,
            isSaving = false,
            userId = 25030024,
            originalUsername = "John Smith",
            formUsername = "John Smith",
            formPhone = "+44 555 5555 55",
            formEmail = "example@example.com",
            formPushNotifications = true
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Header
            AppHeader(
                title = "Edit My Profile",
                onNavigateBack = {},
                onNotifications = {}
            )

            // Se llama a EditProfileContent directamente pero con los datos falsos
            // (Usamos las lambdas vacías {} porque no hacen nada en el preview)
            EditProfileContent(
                uiState = fakeSuccessState,
                onUsernameChanged = {},
                onPhoneChanged = {},
                onEmailChanged = {},
                onPushNotificationsChanged = {},
                onUpdateProfileClick = {},
                darkTheme = false,
                onThemeChange = {}
            )
        }
    }
}