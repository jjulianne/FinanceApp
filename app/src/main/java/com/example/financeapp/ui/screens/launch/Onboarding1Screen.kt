package com.example.financeapp.ui.screens.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.ui.theme.FinWiseDarkGreen
import com.example.financeapp.ui.theme.FinWiseGreen
import com.example.financeapp.core.noRippleClickable

@Composable
fun Onboarding1Screen(
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FinWiseGreen) // Fondo verde principal
    ) {
        // Titulo
        Text(
            text = "Welcome to Expense Manager",
            modifier = Modifier
                .offset(x = 70.dp, y = 123.dp)
                .width(289.dp)
                .height(122.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold, // Poppins SemiBold 600
            lineHeight = 39.sp,
            textAlign = TextAlign.Center,
            color = FinWiseDarkGreen, // #0E3E3E
            letterSpacing = 0.sp
        )

        // Base Shape
        Box(
            modifier = Modifier
                .offset(x = 1.dp, y = 308.dp)
                .width(430.dp)
                .height(624.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color(0xFFF1FFF3)) // #F1FFF3
        )

        // Circulo de fondo
        Box(
            modifier = Modifier
                .offset(x = 99.dp, y = 443.dp)
                .size(248.dp)
                .clip(CircleShape)
                .background(Color(0xFFDFF7E2)) // #DFF7E2
        )

        // Coin
        Image(
            painter = painterResource(id = R.drawable.onboarding_coins),
            contentDescription = "Coins illustration",
            modifier = Modifier
                .offset(x = 72.dp, y = 403.5.dp)
                .size(287.dp)
        )

        // Next
        Box(
            modifier = Modifier
                .offset(x = 181.dp, y = 758.dp)
                .width(69.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Next",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold, // Poppins SemiBold 600
                textAlign = TextAlign.Center,
                color = FinWiseDarkGreen, // #0E3E3E
                letterSpacing = 0.sp,
                modifier = Modifier.noRippleClickable { onNext() }
            )
        }

        // dots
        Row(
            modifier = Modifier
                .offset(x = 194.dp, y = 807.dp)
                .width(42.dp)
                .height(13.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Dot activo (primera pagina)
            Box(
                modifier = Modifier
                    .size(13.dp)
                    .clip(CircleShape)
                    .background(FinWiseGreen) // #00D09E
            )

            // Dot inactivo (segunda pagina)
            Box(
                modifier = Modifier
                    .size(13.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .border(2.dp, FinWiseDarkGreen, CircleShape)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun Onboarding1ScreenPreview() {
    Onboarding1Screen(onNext = {})
}