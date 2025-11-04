package com.example.financeapp.ui.screens.transactions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.components.TransactionListScreen
import com.example.financeapp.core.IncomeContent
import com.example.financeapp.ui.theme.FinWiseGreen // Color activo
import com.example.financeapp.ui.theme.Honeydew // Color pasivo

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
