package com.example.financeapp.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.financeApp.R

@Composable
fun TimeFilterRow(
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = colorResource(id = R.color.light_green),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // 1. Daily
            TimeFilterButton(
                text = "Daily",
                containerColor = colorResource(id = R.color.light_green),
                contentColor = colorResource(id = R.color.fence_green),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            )

            // 2. Weekly
            TimeFilterButton(
                text = "Weekly",
                containerColor = colorResource(id = R.color.light_green),
                contentColor = colorResource(id = R.color.fence_green),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            )

            // 3. Monthly
            TimeFilterButton(
                text = "Monthly",
                containerColor = colorResource(id = R.color.caribbean_green),
                contentColor = colorResource(id = R.color.fence_green),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * Componente Helper simple para replicar el estilo de botón.
 */
@Composable
private fun TimeFilterButton(
    text: String,
    containerColor: Color,
    contentColor: Color,
    shape: RoundedCornerShape,
    modifier: Modifier = Modifier
) {
    Surface(
        color = containerColor,
        shape = shape,
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = contentColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp)
        )
    }

}