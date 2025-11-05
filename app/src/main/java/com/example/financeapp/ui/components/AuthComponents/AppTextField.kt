package com.example.financeapp.ui.components.AuthComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.theme.FinWiseDarkGreen
import com.example.financeapp.ui.theme.FinWiseGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = FinWiseDarkGreen, fontFamily = FontFamily.SansSerif) },
        placeholder = { Text(placeholder, color = Color(0xFF9BBFA8)) },
        singleLine = true,
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
