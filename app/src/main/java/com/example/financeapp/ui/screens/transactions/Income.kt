package com.example.financeapp.ui.screens.transactions



import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.components.TransactionListScreen
import com.example.financeapp.core.IncomeContent

@Composable
fun IncomeScreen(navController: NavController) {
    TransactionListScreen(
        navController = navController,
        content = IncomeContent,
        onIncomeClick = { /* Already on Income screen, do nothing or pop back */ },
        onExpenseClick = { navController.navigate("expense_route") }
    )
}