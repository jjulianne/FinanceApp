package com.example.financeapp.ui.screens.security

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun FingerprintScreen(
    viewModel: FingerprintViewModel = hiltViewModel(),
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onViewFingerprint: (String) -> Unit = {},
    onAddFingerprint: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Header
            AppHeader(
                title = "Fingerprint",
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

            when (val state = uiState) {
                is FingerprintUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is FingerprintUiState.Success -> {
                    // Contenido principal (solo se muestra en estado Success)
                    FingerprintContent(
                        fingerprints = state.fingerprints,
                        onViewFingerprint = onViewFingerprint,
                        onAddFingerprint = onAddFingerprint
                    )
                }
                is FingerprintUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 38.dp)
                    )
                }
            }
        }
    }
}

/**
 * Contenido principal (la lista de items)
 */
@Composable
private fun FingerprintContent(
    fingerprints: List<String>,
    onViewFingerprint: (String) -> Unit,
    onAddFingerprint: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 175.dp)
            .padding(horizontal = 38.dp)
    ) {
        fingerprints.forEach { fingerprintName ->
            FingerprintMenuItem(
                icon = R.drawable.fingerprints,
                text = fingerprintName,
                iconBackgroundColor = Color(0xFF6DB6FE),
                onClick = { onViewFingerprint(fingerprintName) } // Pasa el nombre
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Add A Fingerprint (siempre se muestra)
        FingerprintMenuItem(
            icon = R.drawable.more,
            text = "Add A Fingerprint",
            iconBackgroundColor = Color(0xFF6DB6FE),
            onClick = onAddFingerprint
        )
    }
}


/**
 * Item del menu de huella digital con icono y flecha
 */
@Composable
private fun FingerprintMenuItem(
    icon: Int,
    text: String,
    iconBackgroundColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono con fondo circular
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(iconBackgroundColor.copy(alpha = 0.6f)),
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

        // Flecha derecha
        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Navigate",
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun FingerprintScreenPreview() {
    FinanceAppTheme(darkTheme = false) { // Cambia a 'true' para probar modo oscuro
        FingerprintScreen(darkTheme = false)
    }
}