package com.example.financeapp.ui.screens.transactions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.components.TransactionListScreen
import com.example.financeapp.core.ExpenseContent

@Composable
fun ExpenseScreen(navController: NavController) {
    TransactionListScreen(
        navController = navController,
        content = ExpenseContent,
        onIncomeClick = { navController.navigate("income_route") },
        onExpenseClick = { /* Already on Expense screen, do nothing or pop back */ }
    )
}