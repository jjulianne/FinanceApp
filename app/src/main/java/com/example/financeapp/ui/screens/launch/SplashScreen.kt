package com.example.financeapp.ui.screens.launch

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeApp.R
import com.example.financeapp.domain.use_case.auth.CheckAuthState
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: LaunchViewModel = hiltViewModel()
) {
    // Animaciones para el efecto de aparicion
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(0.8f) }

    var animationFinished by remember { mutableStateOf(false) }

    // Estado de autenticacion del ViewModel
    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(Unit) {
        // Delay inicial de 800ms (animation-delay del Figma)
        delay(800)

        // Animacion de entrada con ease-out de 800ms (animation-duration)
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing // ease-out
            )
        )
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )

        animationFinished = true
    }

    LaunchedEffect(animationFinished, authState) {
        // La navegacion SÓLO procede si la animacion ha terminado Y el ViewModel ha retornado un estado.
        if (animationFinished && authState != null) {
            when (authState) {
                CheckAuthState.AuthState.Authenticated -> {
                    // Si esta autenticado, navega directamente a Home
                    onNavigateToHome()
                }
                CheckAuthState.AuthState.Unauthenticated -> {
                    // Si no esta autenticado (pero ya vio Onboarding), navega a Login
                    onNavigateToLogin()
                }
                CheckAuthState.AuthState.OnboardingRequired -> {
                    // Si necesita Onboarding, navega a OnboardingScreen
                    onNavigateToOnboarding()
                }
                null -> {
                    // Estado inicial de carga, no hacemos nada
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00D09E)), // background: #00D09E del Figma
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.graphicsLayer {
                this.alpha = alpha.value
                scaleX = scale.value
                scaleY = scale.value
            }
        ) {
            // Icono solo (SVG del grafico con flecha)
            Image(
                painter = painterResource(id = R.drawable.ic_finwise_icon),
                contentDescription = "FinWise Icon",
                modifier = Modifier.size(width = 109.dp, height = 115.dp),
                colorFilter = ColorFilter.tint(Color(0xFF0E3E3E)) // Color del icono: #0E3E3E
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto "FinWise" separado
            Text(
                text = "FinWise",
                fontSize = 52.sp,
                fontWeight = FontWeight.SemiBold, // Poppins SemiBold 600
                color = Color(0xFFFFFFFF), // Color del texto: #FFFFFF (blanco)
                letterSpacing = 0.sp
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        onNavigateToHome = {},
        onNavigateToOnboarding = {},
        onNavigateToLogin = {}
    )
}