package com.example.financeapp.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R


@Composable
fun SavingsAndMetricCard(
    // Parámetros para la sección de Ahorros - en viewmodel
    savingsGoalProgress: Float,
    savingsIconId: Int,

    // Parámetros para las Métricas (Revenue/Food) - en viewmodel
    metric1Title: String,
    metric1IconId: Int,
    metric1Amount: String,
    metric2Title: String,
    metric2IconId: Int,
    metric2Amount: String,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = colorResource(id = R.color.caribbean_green),
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 1. Sección de Ahorros
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.8f)
            ) {
                // Contenedor del Círculo de Progreso y el Ícono
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(72.dp)
                ) {
                    // Círculo de Progreso (la línea azul)
                    CircularProgressIndicator(
                        progress = {savingsGoalProgress},
                        modifier = Modifier.size(72.dp),
                        color = colorResource(id = R.color.ocean_blue),
                        strokeWidth = 3.dp,
                        trackColor = Color.White
                    )

                    // Icono
                    Icon(
                        painter = painterResource(id = savingsIconId),
                        contentDescription = "Goal Icon",
                        tint = colorResource(id = R.color.fence_green),
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Texto Inferior
                Text(
                    text = "Savings\nOn Goals",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = colorResource(id = R.color.fence_green),
                    textAlign = TextAlign.Center
                )
            }

            // 2. Separador Vertical
            Spacer(
                modifier = Modifier
                    .width(2.dp)
                    .height(100.dp)
                    .background(colorResource(id = R.color.honeydew))
            )

            // 3. Sección de Métricas
            Column(
                modifier = Modifier.weight(1.2f).padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Primer MetricItem (Revenue)
                MetricItem(
                    iconId = metric1IconId,
                    title = metric1Title,
                    amount = metric1Amount,
                    amountColor = colorResource(id = R.color.fence_green)
                )

                // 4. Separador horizontal
                Spacer(modifier = Modifier.height(12.dp))
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(colorResource(id = R.color.honeydew))
                )
                Spacer(modifier = Modifier.height(12.dp))

                // Segundo MetricItem (Food)
                MetricItem(
                    iconId = metric2IconId,
                    title = metric2Title,
                    amount = metric2Amount,
                    amountColor = colorResource(id = R.color.ocean_blue)
                )
            }
        }
    }
}