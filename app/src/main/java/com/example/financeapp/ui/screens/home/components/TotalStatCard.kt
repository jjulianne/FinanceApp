package com.example.financeapp.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R

@Composable
fun TotalStatCard(
    modifier: Modifier = Modifier,
    iconId: Int,
    title: String,
    amount: String,
    amountColor: Color,
    amountFontWeight: FontWeight
) {

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
            )
        {

            // 1. Ícono
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            // 2. Título
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.fence_green),
            )
        }

        Spacer(modifier = Modifier.size(4.dp))

        // 3. Monto Principal
        Text(
            text = amount,
            fontSize = 24.sp,
            fontWeight = amountFontWeight,
            color = amountColor,
            lineHeight = 24.sp
        )
    }
}