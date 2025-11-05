package com.example.financeapp.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeApp.R
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.components.LoadingScreen

@Composable
fun PasswordSettingsScreen(
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    viewModel: PasswordSettingsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is PasswordSettingsEvent.NavigateBack -> onNavigateBack()
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
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Header
            AppHeader(
                title = "Password Settings",
                onNavigateBack = onNavigateBack,
                onNotifications = { /* Falta implementar notificaciones */ }
            )

            // Tarjeta blanca de fondo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 120.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(MaterialTheme.colorScheme.surface)
            )

            // Formulario de cambio de contrasenia
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 160.dp)
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Current Password
                PasswordField(
                    label = "Current Password",
                    value = state.currentPassword,
                    onValueChange = viewModel::onCurrentPasswordChanged,
                    isVisible = state.isCurrentPasswordVisible,
                    onVisibilityToggle = { viewModel.onToggleVisibility("current") }
                )

                // New Password
                PasswordField(
                    label = "New Password",
                    value = state.newPassword,
                    onValueChange = viewModel::onNewPasswordChanged,
                    isVisible = state.isNewPasswordVisible,
                    onVisibilityToggle = { viewModel.onToggleVisibility("new") }
                )

                // Confirm New Password
                PasswordField(
                    label = "Confirm New Password",
                    value = state.confirmPassword,
                    onValueChange = viewModel::onConfirmPasswordChanged,
                    isVisible = state.isConfirmPasswordVisible,
                    onVisibilityToggle = { viewModel.onToggleVisibility("confirm") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Change Password Button
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = viewModel::onChangePasswordClicked,
                        enabled = !state.isLoading,
                        modifier = Modifier
                            .width(200.dp)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Text(
                            text = "Change Password",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }

            if (state.showSuccess) {
                LoadingScreen(
                    message = "Password Changed\nSuccessfully",
                    isVisible = true,
                    onDismiss = viewModel::onSuccessDialogDismissed,
                    isError = false,
                    durationMillis = 3000L
                )
            }

            if (state.errorMessage != null) {
                LoadingScreen(
                    message = state.errorMessage!!,
                    isVisible = true,
                    onDismiss = viewModel::onErrorDialogDismissed,
                    isError = true,
                    durationMillis = 4000L
                )
            }
        }
    }
}

/**
 * Campo de texto para contrasenias con circulos y toggle de visibilidad
 */
@Composable
private fun PasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Circulos que representan la contrasenia
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(8) { index ->
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index < value.length)
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    else
                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                                )
                        )
                    }
                }

                // Icono de ojo para mostrar/ocultar
                Icon(
                    painter = painterResource(
                        id = if (isVisible) R.drawable.eye_pass_on else R.drawable.eye_pass
                    ),
                    contentDescription = if (isVisible) "Hide password" else "Show password",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onVisibilityToggle()
                        },
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // TextField invisible para capturar el input
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-100).dp), // Fuera de vista
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true
            )
        }
    }
}