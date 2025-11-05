package com.example.financeapp.ui.screens.transactions

// import TransactionListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.financeapp.core.IncomeContent
import com.example.financeapp.ui.theme.FinWiseGreen // Color activo
import com.example.financeapp.ui.theme.Honeydew // Color pasivo
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.components.TransactionListScreen

@Composable
fun IncomeScreen(
    navController: NavController,
    darkTheme: Boolean,
    currentRoute: String,
    onNavigateToHome: () -> Unit,
    onNavigateToAnalysis: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    // En la pantalla Income, el bloque Income es activo (FinWiseGreen)
    val content = IncomeContent.copy(
        activeCardColor = FinWiseGreen
    )

    TransactionListScreen(
        navController = navController,
        content = content,
        onIncomeClick = { /* Ya en Income, no hacer nada */ },
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
fun IncomeScreenPreview() {
    IncomeScreen(
        navController = rememberNavController(),
        darkTheme = false,
        currentRoute = "income_route", // Ruta de ejemplo para el preview
        onNavigateToHome = {},
        onNavigateToAnalysis = {},
        onNavigateToTransactions = {},
        onNavigateToCategory = {},
        onNavigateToProfile = {}
    )
}