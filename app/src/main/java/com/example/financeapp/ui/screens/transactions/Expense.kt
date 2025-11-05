package com.example.financeapp.ui.screens.transactions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.core.ExpenseContent
// import com.example.financeapp.ui.theme.OceanBlue // Color activo
import com.example.financeapp.ui.theme.Honeydew // Color pasivo
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.MaterialTheme
import com.example.financeapp.components.TransactionListScreen

@Composable
fun ExpenseScreen(
    navController: NavController,
    darkTheme: Boolean,
    currentRoute: String,
    onNavigateToHome: () -> Unit,
    onNavigateToAnalysis: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    // En la pantalla Expense, el bloque Expense es activo (OceanBlue)
    val content = ExpenseContent.copy(
        // TODO: Reemplaza esto con tu nuevo color "activo" cuando lo tengas
        activeCardColor = MaterialTheme.colorScheme.primary
    )

    TransactionListScreen(
        navController = navController,
        content = content,
        onIncomeClick = { navController.navigate("income_route") },
        onExpenseClick = { /* Ya en Expense, no hacer nada */ },

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
fun ExpenseScreenPreview() {
    ExpenseScreen(
        navController = rememberNavController(),
        darkTheme = false,
        currentRoute = "expense_route", // Ruta de ejemplo para el preview
        onNavigateToHome = {},
        onNavigateToAnalysis = {},
        onNavigateToTransactions = {},
        onNavigateToCategory = {},
        onNavigateToProfile = {}
    )
}