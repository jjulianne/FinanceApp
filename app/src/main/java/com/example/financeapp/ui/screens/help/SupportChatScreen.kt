package com.example.financeapp.ui.screens.help

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeApp.R
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun SupportChatScreen(
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    viewModel: SupportChatViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

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
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Header
            AppHeader(
                title = "Online Support",
                onNavigateBack = onNavigateBack,
                onNotifications = { /* Falta implementar notificaciones */ }
            )

            // Tarjeta blanca de fondo Y Contenido principal (fusionados)
            Column(
                modifier = Modifier
                    .padding(top = 135.dp) // Reemplaza al offset
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    // Es muy imporante poner el padding despues del brackground porque me hizo renegar 5 horas
                    .padding(bottom = innerPadding.calculateBottomPadding())
                    .padding(top = 24.dp) // Padding interno para el contenido
            ) {
                // Tabs ("Support Assistant", "Help Center")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ChatTabButton(
                        text = "Support Assistant",
                        isSelected = state.selectedTab == "Support Assistant",
                        onClick = { viewModel.onTabSelected("Support Assistant") },
                    )
                    ChatTabButton(
                        text = "Help Center",
                        isSelected = state.selectedTab == "Help Center",
                        onClick = { viewModel.onTabSelected("Help Center") },
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Lista de Mensajes
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    reverseLayout = true, // Para que el chat empiece desde abajo
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Invertimos la lista si no usamos reverseLayout
                    items(state.messages.reversed()) { message ->
                        ChatMessageBubble(message = message)
                    }
                }

                // Input Bar (fijo abajo)
                ChatInputBar(
                    value = state.currentInputText,
                    onValueChange = viewModel::onInputTextChanged,
                    onSend = viewModel::onSendMessage
                )
            }

            // Overlay de carga
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

/**
 * Burbuja de mensaje de chat
 */
@Composable
private fun ChatMessageBubble(message: ChatMessage) {
    val horizontalAlignment = if (message.isFromUser) Alignment.End else Alignment.Start
    val bubbleColor = if (message.isFromUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (message.isFromUser) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
    val cornerShape = if (message.isFromUser) {
        RoundedCornerShape(16.dp, 16.dp, 4.dp, 16.dp) // Esquina inf-izq aguda
    } else {
        RoundedCornerShape(16.dp, 16.dp, 16.dp, 4.dp) // Esquina inf-der aguda
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = horizontalAlignment
    ) {
        // La burbuja de texto
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f) // Max 80% del ancho
                .background(bubbleColor, cornerShape)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = message.text,
                color = textColor,
                fontSize = 15.sp
            )
        }
        // Timestamp
        Text(
            text = message.timestamp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
        )
    }
}

/**
 * Barra de input de chat en la parte inferior
 */
@Composable
private fun ChatInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.primary) // Fondo teal principal
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Boton Camara
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { /* Faltaria implementar la camara */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(18.dp), // Tamaño del ícono
                painter = painterResource(id = R.drawable.camera_green),
                contentDescription = "Attach image",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Campo de texto
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .height(26.dp),
            textStyle = LocalTextStyle.current.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                // Caja para el fondo blanco y placeholder
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface) // Fondo blanco
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text("Write Here...", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f))
                    }
                    innerTextField()
                }
            }
        )

        // Boton Microfono
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { /* Faltaria implementar microfono */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.microphone),
                contentDescription = "Record audio",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Boton Enviar
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { onSend() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.send),
                contentDescription = "Send",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Boton de tab ("Support Assistant" / "Help Center")
 */
@Composable
private fun ChatTabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(backgroundColor)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun SupportChatScreenPreview() {
    FinanceAppTheme(darkTheme = false) {
        SupportChatScreen(darkTheme = false)
    }
}