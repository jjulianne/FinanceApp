package com.example.financeapp.ui.screens

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
import androidx.compose.runtime.remember
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
import com.example.financeApp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToNext: () -> Unit
) {
    // Animaciones para el efecto de aparicion
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(0.8f) }

    LaunchedEffect(Unit) {
        // Delay inicial de 800ms (animation-delay del Figma)
        delay(800)

        // Animación de entrada con ease-out de 800ms (animation-duration)
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

        // Mostrar el splash por un momento antes de navegar
        delay(1000)

        // Navegar a la siguiente pantalla (1 - B - Launch segun Figma)
        onNavigateToNext()
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
            // Icono solo (SVG del gráfico con flecha)
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
                fontSize = 52.sp, // Según Figma: 52.14px
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
    SplashScreen(onNavigateToNext = {})
}