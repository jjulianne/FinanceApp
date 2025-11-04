package com.example.financeapp.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R

@Composable
fun ExpenseProgressIndicator(
    progress: Float,
    limitAmount: String,
    modifier: Modifier = Modifier
) {

    // 1. Contenedor principal de la barra
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.honeydew))
    ) {
        // 2. Barra de progreso (la parte llena)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                .background(colorResource(id = R.color.fence_green) )
                .align(Alignment.CenterStart)
        ) {
            // 3. Texto del Porcentaje
            Text(
                text = "${(progress * 100).toInt()}%",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 30.dp),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }

        // 4. Texto del Límite
        Text(
            text = limitAmount,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp),
            color = colorResource(id = R.color.fence_green) ,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic
        )
    }
}