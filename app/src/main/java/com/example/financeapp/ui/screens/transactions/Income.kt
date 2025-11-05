package com.example.financeapp.ui.screens.transactions

import TransactionListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import com.example.financeapp.core.IncomeContent
import com.example.financeapp.ui.theme.FinWiseGreen // Color activo
import com.example.financeapp.ui.theme.Honeydew // Color pasivo
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
@Composable
fun IncomeScreen(navController: NavController) {
    // En la pantalla Income, el bloque Income es activo (FinWiseGreen)
    val content = IncomeContent.copy(
        activeCardColor = FinWiseGreen
    )

    TransactionListScreen(
        navController = navController,
        content = content,
        onIncomeClick = { /* Ya en Income, no hacer nada */ },
        onExpenseClick = { navController.navigate("expense_route") }
    )
}



// (Añadir dentro del mismo archivo donde está IncomeScreen)


@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun IncomeScreenPreview() {
    IncomeScreen(navController = rememberNavController())
}
