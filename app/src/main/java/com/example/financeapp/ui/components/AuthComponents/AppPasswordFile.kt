package com.example.financeapp.ui.components.AuthComponents
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.financeApp.R
import com.example.financeapp.ui.theme.FinWiseDarkGreen
import com.example.financeapp.ui.theme.FinWiseGreen

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AppPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = FinWiseDarkGreen,
                fontFamily = FontFamily.SansSerif
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFF9BBFA8),
                fontFamily = FontFamily.SansSerif
            )
        },
        singleLine = true,
        trailingIcon = {

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(id = R.drawable.eye_pass),
                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    tint = FinWiseDarkGreen
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE6F7EB),
            unfocusedContainerColor = Color(0xFFE6F7EB),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = FinWiseGreen
        ),
        modifier = modifier.fillMaxWidth()
    )
}



