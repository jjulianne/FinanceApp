package com.example.financeapp.ui.screens.transactions

import TransactionListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import com.example.financeapp.core.ExpenseContent
import com.example.financeapp.ui.theme.OceanBlue // Color activo
import com.example.financeapp.ui.theme.Honeydew // Color pasivo
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
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


// (Añadir dentro del mismo archivo donde está ExpenseScreen)


@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun ExpenseScreenPreview() {
    ExpenseScreen(navController = rememberNavController())
}
