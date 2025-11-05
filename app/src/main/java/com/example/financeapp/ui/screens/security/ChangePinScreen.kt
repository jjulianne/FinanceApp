package com.example.financeapp.ui.screens.security

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
fun ChangePinScreen(
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    viewModel: ChangePinViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.navigationEvents.collect {
            onNavigateBack() // El VM nos ordena navegar
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
                title = "Change Pin",
                onNavigateBack = onNavigateBack,
                onNotifications = { /* Falta implementar notificaciones */ }
            )

            // Tarjeta blanca de fondo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 135.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(MaterialTheme.colorScheme.surface)
            )

            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 190.dp)
                    .padding(horizontal = 38.dp)
            ) {
                // Current Pin
                PinInputField(
                    label = "Current Pin",
                    value = state.currentPin, // 5. Usamos el estado
                    onValueChange = viewModel::onCurrentPinChanged, // 6. Notificamos al VM
                    isVisible = state.isCurrentPinVisible,
                    onToggleVisibility = { viewModel.onToggleVisibility("current") }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // New Pin
                PinInputField(
                    label = "New Pin",
                    value = state.newPin,
                    onValueChange = viewModel::onNewPinChanged,
                    isVisible = state.isNewPinVisible,
                    onToggleVisibility = { viewModel.onToggleVisibility("new") }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Confirm Pin
                PinInputField(
                    label = "Confirm Pin",
                    value = state.confirmPin,
                    onValueChange = viewModel::onConfirmPinChanged,
                    isVisible = state.isConfirmPinVisible,
                    onToggleVisibility = { viewModel.onToggleVisibility("confirm") }
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Boton Change Pin
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = viewModel::onChangePinClicked, // 7. Solo notificamos
                        enabled = state.isButtonEnabled && !state.isLoading, // 8. El estado controla
                        modifier = Modifier
                            .width(207.dp)
                            .height(45.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Text(
                            text = "Change Pin",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }

            // 9. El estado controla los diálogos de éxito o error
            if (state.showSuccess) {
                LoadingScreen(
                    message = "Pin Has Been\nChanged Successfully",
                    isVisible = true,
                    onDismiss = viewModel::onSuccessDialogDismissed,
                    isError = false,
                    durationMillis = 4000L
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
 * Campo de entrada de PIN con puntos y toggle de visibilidad
 */
@Composable
private fun PinInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onToggleVisibility: () -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Puntos que representan el PIN
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(4) { index ->
                        Box(
                            modifier = Modifier
                                .size(12.dp)
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
                    contentDescription = if (isVisible) "Hide pin" else "Show pin",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onToggleVisibility()
                        },
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // TextField invisible para capturar el input
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .matchParentSize(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true
            )
        }
    }
}
