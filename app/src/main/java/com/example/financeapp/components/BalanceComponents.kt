package com.example.financeapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.material3.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Notifications

import com.example.financeApp.R
import com.example.financeapp.core.GreyText // Asumido
import com.example.financeapp.ui.theme.* // Importamos los colores de FinWise

// --- Colores de la barra de progreso (Usando Void) ---
val ProgressTrackColor = FinWiseWhite // Fondo blanco
val ProgressIndicatorColor = Void // Progreso muy oscuro (usando Void)

// --- 1. Notification Icon (Con fondo blanco) ---
@Composable
fun NotificationIcon() {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(FinWiseWhite),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Notifications,
            contentDescription = "Notifications",
            tint = FinWiseGreen,
            modifier = Modifier.size(24.dp)
        )
    }
}

// --- 2. Balance Header Item (Ajustado para Figma) ---
@Composable
fun BalanceHeaderItem(
    title: String,
    amount: String,
    iconResId: Int,
    isExpense: Boolean
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                tint = FinWiseGreen,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = title,
                color = FinWiseWhite.copy(0.7f),
                fontSize = 14.sp
            )
        }

        Text(
            text = amount,
            color = if (isExpense) LightBlue else FinWiseWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

// --- 3. Expense Progress Indicator (Ajuste de altura y Void) ---
@Composable
fun ExpenseProgressIndicator(
    progress: Float, // Valor entre 0.0f y 1.0f
    totalLimit: String, // E.g., "$20,000.00"
    percentage: String, // E.g., "30%"
    @Suppress("RequiredResourceName")
    iconResId: Int = R.drawable.check_pressed
) {
    val safeProgress = progress.coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Contenedor de la barra de progreso
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .clip(RoundedCornerShape(50))
                .background(ProgressTrackColor)
        ) {
            // Sección de progreso
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(safeProgress)
                    .clip(RoundedCornerShape(50))
                    .background(ProgressIndicatorColor)
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                // Texto de porcentaje
                if (safeProgress > 0.15f) {
                    Text(
                        text = percentage,
                        color = ProgressTrackColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Texto del límite total
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = totalLimit,
                    color = ProgressIndicatorColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Texto de estado
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "Good",
                tint = FinWiseWhite,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "$percentage Of Your Expenses, Looks Good.",
                color = FinWiseWhite,
                fontSize = 14.sp
            )
        }
    }
}

// --- 4. Month Header (Ajustado para Figma) ---
@Composable
fun MonthHeader(month: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Texto más oscuro (Void)
        Text(month, fontWeight = FontWeight.SemiBold, fontSize = 22.sp, color = Void)
    }
}

// --- 5. Transaction Item (Ajustado para Figma y colores) ---
@Composable
fun TransactionItem(
    iconResId: Int,
    title: String,
    dateAndTime: String,
    category: String,
    amount: String,
    amountColor: Color // Usado para ingresos
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = FinWiseWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Column (Usamos LightBlue)
            val iconTint = LightBlue

            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(iconTint.copy(alpha = 0.1f))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = iconResId),
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(Modifier.width(16.dp))

            // Details Column (Title and Date)
            Column(modifier = Modifier.width(100.dp)) {
                Text(title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Void) // Título oscuro (Void)
                Text(dateAndTime, fontSize = 12.sp, color = FinWiseDarkGreen)
            }

            // Separator 1 & Category Column
            Row(
                modifier = Modifier.weight(1f).padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Separador Verde Fino 1
                Divider(
                    color = FinWiseGreen.copy(alpha = 0.5f), // FinWiseGreen semitransparente
                    modifier = Modifier.height(30.dp).width(1.dp)
                )
                Spacer(Modifier.width(12.dp))
                // Texto de categoría
                Text(category, fontSize = 14.sp, color = FinWiseDarkGreen)
                Spacer(Modifier.width(12.dp))
            }

            // Separator 2 & Amount Column
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                // Separador Verde Fino 2
                Divider(
                    color = FinWiseGreen.copy(alpha = 0.5f), // FinWiseGreen semitransparente
                    modifier = Modifier.height(30.dp).width(1.dp)
                )
                Spacer(Modifier.width(12.dp))

                // Monto (LightBlue para egreso, amountColor para ingreso)
                val finalAmountColor = if (amount.contains("-")) LightBlue else amountColor

                Text(
                    amount,
                    color = finalAmountColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.width(80.dp),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
