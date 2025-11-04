package com.example.financeapp.ui.screens.transactions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.components.TransactionListScreen
import com.example.financeapp.core.ExpenseContent
import com.example.financeapp.ui.theme.OceanBlue // Color activo
import com.example.financeapp.ui.theme.Honeydew // Color pasivo

@Composable
fun ExpenseScreen(navController: NavController) {
    // En la pantalla Expense, el bloque Expense es activo (OceanBlue)
    val content = ExpenseContent.copy(
        activeCardColor = OceanBlue
    )

    TransactionListScreen(
        navController = navController,
        content = content,
        onIncomeClick = { navController.navigate("income_route") },
        onExpenseClick = { /* Ya en Expense, no hacer nada */ }
    )
}
