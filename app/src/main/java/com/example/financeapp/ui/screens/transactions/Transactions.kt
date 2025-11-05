package com.example.financeapp.ui.screens.transactions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.core.TransactionsContent
import com.example.financeapp.ui.theme.Honeydew // Importamos Honeydew
import com.example.financeapp.ui.theme.FinWiseGreen
// import com.example.financeapp.ui.theme.OceanBlue // Ya no existe
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
// Este import es el correcto
import com.example.financeapp.components.TransactionListScreen

@Composable
fun TransactionsScreen(
    navController: NavController,
    darkTheme: Boolean,
    currentRoute: String,
    onNavigateToHome: () -> Unit,
    onNavigateToAnalysis: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    // En la pantalla principal de Transactions, ambos bloques son pasivos (Honeydew)
    val content = TransactionsContent.copy(
        activeCardColor = Honeydew // Usamos Honeydew para que SummaryBlockCard sepa que es pasiva
    )

    TransactionListScreen(
        navController = navController,
        content = content,
        onIncomeClick = { navController.navigate("income_route") },
        onExpenseClick = { navController.navigate("expense_route") },

        // Parámetros pasados al BottomNavBar
        currentRoute = currentRoute,
        darkTheme = darkTheme,
        onNavigateToHome = onNavigateToHome,
        onNavigateToAnalysis = onNavigateToAnalysis,
        onNavigateToTransactions = onNavigateToTransactions,
        onNavigateToCategory = onNavigateToCategory,
        onNavigateToProfile = onNavigateToProfile
    )
}


@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun TransactionsScreenPreview() {
    TransactionsScreen(
        navController = rememberNavController(),
        darkTheme = false,
        currentRoute = "transactions_route", // Ruta de ejemplo para el preview
        onNavigateToHome = {},
        onNavigateToAnalysis = {},
        onNavigateToTransactions = {},
        onNavigateToCategory = {},
        onNavigateToProfile = {}
    )
}