package com.example.financeapp.ui.screens.transactions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.components.TransactionListScreen
import com.example.financeapp.core.TransactionsContent

@Composable
fun TransactionsScreen(navController: NavController) {
    TransactionListScreen(
        navController = navController,
        content = TransactionsContent,
        onIncomeClick = { navController.navigate("income_route") },
        onExpenseClick = { navController.navigate("expense_route") }
    )
}