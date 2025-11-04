package com.example.financeapp.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.FinWiseGreen
import kotlinx.coroutines.delay

/**
 * Pantalla de exito/confirmacion reutilizable
 * Muestra una animacion de circulo con un mensaje
 */
@Composable
fun LoadingScreen(
    message: String,               // Mensaje que se mostrara al finalizar la animacion
    isVisible: Boolean,            // Controla si el dialogo esta visible o no
    onDismiss: () -> Unit,         // Accion al cerrar automaticamente el dialogo
    durationMillis: Long = 4000L,  // Duracion total antes del auto-cierre
    isError: Boolean = false       // Define si el resultado final es éxito (false) o error (true)
) {
    // Estado de la animacion (puntos → check → X)
    var animationState by remember { mutableStateOf(0) }
    // Texto mostrado durante el proceso (inicia en "Loading...")
    var displayMessage by remember { mutableStateOf("Loading...") }

    /**
     * Secuencia:
     *  - Primero se muestran los puntos de carga
     *  - Luego el check o la X según isError
     *  - Finalmente se muestra el mensaje recibido por parametro
     *  - Se cierra automaticamente tras la duracion indicada
     */
    LaunchedEffect(isVisible) {
        if (isVisible) {
            animationState = 0
            displayMessage = "Loading..."
            delay(2500)
            animationState = if (isError) 4 else 3 // 3 = check, 4 = X
            displayMessage = message
            delay(durationMillis - 2500)
            onDismiss()
        }
    }

    if (isVisible) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(FinWiseGreen),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Animacion principal del circulo con estados dinamicos
                    SuccessCircleAnimation(animationState)

                    Spacer(modifier = Modifier.height(32.dp))

                    // Mensaje mostrado debajo del circulo
                    Text(
                        text = displayMessage,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 40.dp)
                    )
                }
            }
        }
    }
}

/**
 * Animacion del círculo con punto central
 * Ciclos:
 *  1 punto -> 2 puntos -> 3 puntos -> check -> X
 */
@Composable
private fun SuccessCircleAnimation(state: Int) {
    // Estado interno SOLO para la animación de los puntos (0, 1, 2)
    var loadingDotState by remember { mutableStateOf(0) }

    // Este efecto se ejecuta solo cuando el estado es '0' (cargando)
    LaunchedEffect(state) {
        if (state == 0) {
            // Bucle 'while' que se ejecuta solo durante la carga
            while (true) {
                delay(500) // Cambia cada 500ms
                loadingDotState = (loadingDotState + 1) % 3 // 3 estados de puntos: 0, 1, 2
            }
        }
        // Cuando 'state' cambie a 3 o 4, este LaunchedEffect se cancelara
        // y el bucle 'while' se detendra.
    }

    // Animacion de rotacion del círculo (esta estaba bien)
    val infiniteTransition = rememberInfiniteTransition(label = "circle")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Canvas(
        modifier = Modifier.size(120.dp)
    ) {
        val canvasSize = size.minDimension
        val center = Offset(canvasSize / 2, canvasSize / 2)
        val radius = canvasSize / 2 - 20.dp.toPx()

        // Circulo exterior (con rotacion)
        drawCircle(
            color = Color.White,
            radius = radius,
            center = center,
            style = Stroke(
                width = 8.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        when (state) {
            0 -> {
                when (loadingDotState) {
                    0 -> {
                        // punto central
                        drawCircle(
                            color = Color.White,
                            radius = 8.dp.toPx(),
                            center = center
                        )
                    }
                    1 -> {
                        // 2 puntos
                        drawCircle(
                            color = Color.White,
                            radius = 6.dp.toPx(),
                            center = Offset(center.x - 15.dp.toPx(), center.y)
                        )
                        drawCircle(
                            color = Color.White,
                            radius = 6.dp.toPx(),
                            center = Offset(center.x + 15.dp.toPx(), center.y)
                        )
                    }
                    2 -> {
                        // 3 puntos
                        drawCircle(
                            color = Color.White,
                            radius = 6.dp.toPx(),
                            center = Offset(center.x - 20.dp.toPx(), center.y)
                        )
                        drawCircle(
                            color = Color.White,
                            radius = 6.dp.toPx(),
                            center = center
                        )
                        drawCircle(
                            color = Color.White,
                            radius = 6.dp.toPx(),
                            center = Offset(center.x + 20.dp.toPx(), center.y)
                        )
                    }
                }
            }
            // Good path check
            3 -> {
                // Check
                val checkPath = androidx.compose.ui.graphics.Path().apply {
                    moveTo(center.x - 20.dp.toPx(), center.y)
                    lineTo(center.x - 5.dp.toPx(), center.y + 15.dp.toPx())
                    lineTo(center.x + 20.dp.toPx(), center.y - 15.dp.toPx())
                }
                drawPath(
                    path = checkPath,
                    color = Color.White,
                    style = Stroke(
                        width = 6.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
            // Bad path X
            4 -> {
                // X
                drawLine(
                    color = Color.White,
                    start = Offset(center.x - 20.dp.toPx(), center.y - 20.dp.toPx()),
                    end = Offset(center.x + 20.dp.toPx(), center.y + 20.dp.toPx()),
                    strokeWidth = 6.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(center.x + 20.dp.toPx(), center.y - 20.dp.toPx()),
                    end = Offset(center.x - 20.dp.toPx(), center.y + 20.dp.toPx()),
                    strokeWidth = 6.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun SuccessScreenPreview() {
    FinanceAppTheme {
        LoadingScreen(
            message = "Operation Completed Successfully",
            isVisible = true,
            onDismiss = {},
            isError = false
        )
    }
}
