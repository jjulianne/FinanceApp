package com.example.financeapp.ui.screens.transactions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.components.TransactionListScreen
import com.example.financeapp.core.TransactionsContent
import com.example.financeapp.ui.theme.Honeydew // Importamos Honeydew
import com.example.financeapp.ui.theme.FinWiseGreen
import com.example.financeapp.ui.theme.OceanBlue

@Composable
fun TransactionsScreen(navController: NavController) {
    // En la pantalla principal de Transactions, ambos bloques son pasivos (Honeydew)
    val content = TransactionsContent.copy(
        activeCardColor = Honeydew // Usamos Honeydew para que SummaryBlockCard sepa que es pasiva
    )

    TransactionListScreen(
        navController = navController,
        content = content,
        onIncomeClick = { navController.navigate("income_route") },
        onExpenseClick = { navController.navigate("expense_route") }
    )
}
