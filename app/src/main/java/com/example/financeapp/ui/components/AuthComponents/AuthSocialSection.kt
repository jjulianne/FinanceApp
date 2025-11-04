package com.example.financeapp.ui.components.AuthComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.ui.theme.FinWiseDarkGreen

@Composable
fun AuthSocialSection(
    onFacebookClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    text: String = "or sign up with"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Texto de separación
        Text(
            text = text,
            color = FinWiseDarkGreen.copy(alpha = 0.6f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Íconos sociales
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Facebook Login",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onFacebookClick() }
            )
*/
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Login",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onGoogleClick() }
            )
        }
    }
}


