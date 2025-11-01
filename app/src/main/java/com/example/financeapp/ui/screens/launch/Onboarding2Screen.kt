package com.example.financeapp.ui.screens.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.ui.theme.FinWiseDarkGreen
import com.example.financeapp.ui.theme.FinWiseGreen

@Composable
fun Onboarding2Screen(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FinWiseGreen) // Fondo verde principal
    ) {
        // Seccion superior con texto
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿Are You Ready To",
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = FinWiseDarkGreen,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Take Control Of",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = FinWiseDarkGreen,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Your Finaces?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = FinWiseDarkGreen,
                textAlign = TextAlign.Center
            )
        }

        // Seccion central con la imagen del celular
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .align(Alignment.Center)
                .offset(y = 40.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(FinWiseGreen.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            // Imagen del celular desde el drawable
            Image(
                painter = painterResource(id = R.drawable.onboarding_phone),
                contentDescription = "Phone illustration",
                modifier = Modifier.size(250.dp)
            )
        }

        // Seccion inferior con boton y indicadores
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Boton "Next"
            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FinWiseDarkGreen
                ),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = FinWiseGreen
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Indicadores de pagina (dots)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Pagina inactiva (primera)
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(FinWiseDarkGreen.copy(alpha = 0.3f))
                )
                // Pagina activa (segunda)
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(FinWiseDarkGreen)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun Onboarding2ScreenPreview() {
    Onboarding2Screen(
        onNext = {},
        onBack = {}
    )
}