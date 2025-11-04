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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeApp.R
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.components.LoadingScreen
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun DeleteAccountScreen(
    viewModel: DeleteAccountViewModel = hiltViewModel(),
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onAccountDeleted: () -> Unit = {}, // Se llamará para navegar al Login
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.events.collect { event ->
            when (event) {
                is DeleteAccountEvent.OnAccountDeleted -> onAccountDeleted()
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
                title = "Delete Account",
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
                    // Usamos el color de superficie (fondo de tarjetas)
                    .background(MaterialTheme.colorScheme.surface)
            )

            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 140.dp)
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Titulo de advertencia
                Text(
                    text = "Are You Sure You Want To Delete\nYour Account?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    // Texto sobre la superficie (tarjeta)
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Caja de informacion de advertencia
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(20.dp)
                ) {
                    Column {
                        Text(
                            text = "This action will permanently delete all of your data, and you will not be able to recover it. Please keep the following in mind before proceeding:",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            // Texto sobre la variante de superficie
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Lista de consecuencias
                        ConsequenceItem("All your expenses, income and associated transactions will be eliminated.")
                        Spacer(modifier = Modifier.height(8.dp))
                        ConsequenceItem("You will not be able to access your account or any related information.")
                        Spacer(modifier = Modifier.height(8.dp))
                        ConsequenceItem("This action cannot be undone.")
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Instruccion para confirmar
                Text(
                    text = "Please Enter Your Password To Confirm\nDeletion Of Your Account.",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    // Texto sobre la superficie (tarjeta)
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Campo de contrasenia
                DeleteAccountPasswordField(
                    value = state.confirmPassword,
                    onValueChange = viewModel::onPasswordChanged,
                    isVisible = state.isPasswordVisible,
                    onVisibilityToggle = viewModel::onToggleVisibility
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botones de accion
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Boton de eliminar cuenta
                    Button(
                        onClick = viewModel::onDeleteClicked,
                        enabled = !state.isLoading, // Deshabilitado si está cargando
                        modifier = Modifier
                            .width(220.dp)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.secondary
                            )
                        } else {
                            Text(
                                text = "Yes, Delete Account",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }

                    // Boton de cancelar
                    Button(
                        onClick = onNavigateBack, // Llama al onNavigateBack
                        enabled = !state.isLoading, // Deshabilitado si está cargando
                        modifier = Modifier
                            .width(220.dp)
                            .height(44.dp),
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

    // Este es el popup (CONECTADO)
    if (state.showConfirmDialog) {
        DeleteConfirmationPopup(
            onConfirm = viewModel::onConfirmDeletion,
            onCancel = viewModel::onCancelDialog
        )
    }

    // Popups de éxito y error
    if (state.showSuccess) {
        LoadingScreen(
            message = "Account Deleted Successfully", // Mensaje de éxito
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

/**
 * Item de lista para las consecuencias de eliminar la cuenta
 */
@Composable
private fun ConsequenceItem(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Bullet point
        Text(
            text = "•",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 16.sp
        )
    }
}

/**
 * Campo de contrasenia con circulos para confirmar eliminacion de cuenta
 */
@Composable
private fun DeleteAccountPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(
                // Fondo del campo de contrasenia (variante de superficie)
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

/**
 * Este es el Dialog (ventana emergente) que se llama desde la pantalla principal.
 * Proporciona el fondo oscurecido.
 */
@Composable
private fun DeleteConfirmationPopup(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    // Dialog proporciona el fondo oscurecido (scrim) automaticamente
    Dialog(onDismissRequest = onCancel) {
        // Llama al contenido real de la tarjeta
        DeleteConfirmationPopupContent(
            onConfirm = onConfirm,
            onCancel = onCancel
        )
    }
}

@Composable
private fun DeleteConfirmationPopupContent(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp), // Esquinas redondeadas como en la imagen
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface // Fondo blanco
        ),
        modifier = Modifier.width(320.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Titulo
            Text(
                text = "Delete Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitulo (de la imagen)
            Text(
                text = "Are You Sure You Want To Log Out?", // No entiendo porque dice log out si es delete account pero bueno, pongo igual que el figma
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Texto de cuerpo (de la imagen)
            Text(
                text = "By deleting your account, you agree that you understand the consequences of this action and that you agree to permanently delete your account and all associated data.",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Boton "Yes, Delete Account" (Color de la imagen)
            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    // Color verde brillante de tu imagen
                    containerColor = Color(0xFF1DE9B6)
                ),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Yes, Delete Account",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    // Usamos el color de texto de tu boton original
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Boton "Cancel" (Color de la imagen)
            Button(
                onClick = onCancel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    // Color verde palido de tu imagen
                    containerColor = Color(0xFFD4F8E8)
                ),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    // Usamos el color de texto de tu boton original
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun DeleteAccountScreenPreview() {
    FinanceAppTheme(darkTheme = false) {
        DeleteAccountScreen(
            darkTheme = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteConfirmationPopupPreview() {
    FinanceAppTheme(darkTheme = false) {
        // Apuntamos al "Content", no al "Dialog"
        DeleteConfirmationPopupContent(
            onConfirm = {},
            onCancel = {}
        )
    }
}