package com.example.financeapp.ui.screens.home.components

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.domain.model.TransactionItem

@Composable
fun TransactionItemRow(item: TransactionItem) {
    // recursos
    val amountColor = if (item.isExpense) colorResource(id = R.color.ocean_blue) else colorResource(id = R.color.fence_green)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. Icono
        Box(
            modifier = Modifier
                .size(57.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(colorResource(id = item.iconBackgroundColorId)),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = colorResource(id = R.color.white),
                modifier = Modifier.size(26.dp))
        }

        Spacer(modifier = Modifier.width(16.dp))

        //  2. COLUMNA 1: Título y Fecha
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color =  colorResource(id = R.color.fence_green),
                lineHeight = 15.sp)

            Text(text = item.timeDate, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = colorResource(id = R.color.ocean_blue), lineHeight = 12.sp)
        }

        // 3. COLUMNAS 2 y 3: Estructura de las Columnas de Categoría y Monto
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Divisor 1
            Spacer(modifier = Modifier.width(16.dp))
            VerticalDivider(color = colorResource(id = R.color.caribbean_green), thickness = 2.dp, height = 40.dp)
            Spacer(modifier = Modifier.width(16.dp))

            // COLUMNA 2: Categoría
            Column(
                modifier = Modifier.width(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = item.category, fontSize = 12.sp, fontWeight = FontWeight.Light, color =  colorResource(id = R.color.fence_green), lineHeight = 15.sp)
            }

            // Divisor 2
            Spacer(modifier = Modifier.width(16.dp))
            VerticalDivider(color = colorResource(id = R.color.caribbean_green), thickness = 2.dp, height = 40.dp)
            Spacer(modifier = Modifier.width(16.dp))

            // COLUMNA 3: Monto
            Column(
                modifier = Modifier.width(75.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = item.amount, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = amountColor, lineHeight = 15.sp)
            }
        }
    }
}

@Composable
private fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color,
    thickness: Dp = 1.dp,
    height: Dp = 30.dp
) {
    Spacer(
        modifier = modifier
            .width(thickness)
            .height(height)
            .background(color)
    )
}