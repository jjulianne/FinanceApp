package com.example.financeapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.screens.balance.AccountBalanceScreen
import com.example.financeapp.ui.screens.launch.Onboarding1Screen
import com.example.financeapp.ui.screens.launch.Onboarding2Screen
import com.example.financeapp.ui.screens.launch.SplashScreen
import com.example.financeapp.ui.screens.launch.WelcomeScreen
import com.example.financeapp.ui.screens.transactions.ExpenseScreen
import com.example.financeapp.ui.screens.transactions.IncomeScreen
import com.example.financeapp.ui.screens.transactions.TransactionsScreen
import com.example.financeApp.R // Assuming this R import is correct

// --- Sealed class for Bottom Navigation Items (Used by BottomNavBar) ---
sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    object Home : BottomNavItem("home_route", R.drawable.home_nav, "Home")
    object Stats : BottomNavItem("stats_route", R.drawable.analysis_nav, "Stats")
    object Transfer : BottomNavItem("transfer_route", R.drawable.transaction_nav, "Transfer")
    object Wallet : BottomNavItem("wallet_route", R.drawable.category_nav, "Wallet")
    object Profile : BottomNavItem("profile_route", R.drawable.profile_nav, "Profile")
}


// --- Application Screen Routes ---
sealed class Screen(val route: String) {
    // Rutas de Inicio (Launch)
    object Splash : Screen("splash_route")
    object Onboarding1 : Screen("onboarding1_route")
    object Onboarding2 : Screen("onboarding2_route")
    object Welcome : Screen("welcome_route")

    // Rutas de Features principales
    object Home : Screen(BottomNavItem.Home.route) // AccountBalanceScreen
    object Transactions : Screen(BottomNavItem.Transfer.route) // TransactionsScreen
    object Categories : Screen("categories_route")
    object SavingGoals : Screen("saving_goals_route")

    // Rutas de Autenticación
    object Login : Screen("login_route")
    object SignUp : Screen("signup_route")
    object ForgotPassword : Screen("forgot_password_route")

    // Rutas de Filtros de Transactions
    object Income : Screen("income_route")
    object Expense : Screen("expense_route")

    // Rutas de Perfil y Configuración
    object ProfileRoute : Screen(BottomNavItem.Profile.route)
    object Settings : Screen("settings_route")
    object Security : Screen("security_route")
}

@Composable
fun FinWiseNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Pantalla Splash (inicial)
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(Screen.Onboarding1.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantallas Onboarding y Welcome
        composable(Screen.Onboarding1.route) { Onboarding1Screen(onNext = { navController.navigate(Screen.Onboarding2.route) }) }
        composable(Screen.Onboarding2.route) { Onboarding2Screen(onNext = { navController.navigate(Screen.Welcome.route) }, onBack = { navController.popBackStack() }) }
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) },
                onNavigateToForgotPassword = { navController.navigate(Screen.ForgotPassword.route) }
            )
        }

        // Rutas de Autenticacion
        composable(Screen.Login.route) { PlaceholderScreen("Login Screen") }
        composable(Screen.SignUp.route) { PlaceholderScreen("Sign Up Screen") }
        composable(Screen.ForgotPassword.route) { PlaceholderScreen("Forgot Password Screen") }

        // Pantallas Principales
        composable(Screen.Home.route) {
            AccountBalanceScreen(navController)
        }

        composable(Screen.Transactions.route) {
            TransactionsScreen(navController = navController)
        }

        // Pantallas de Filtro
        composable(Screen.Expense.route) {
            ExpenseScreen(navController)
        }
        composable(Screen.Income.route) {
            IncomeScreen(navController)
        }

        // Mapeo de otros items del BottomNav
        composable(BottomNavItem.Stats.route) { PlaceholderScreen("Stats Screen") }
        composable(Screen.Categories.route) { PlaceholderScreen("Categories Screen") } // Assuming Wallet/Categories
        composable(Screen.ProfileRoute.route) { PlaceholderScreen("Profile Screen") }

        // Otras Rutas
        composable(Screen.SavingGoals.route) { PlaceholderScreen("Saving Goals Screen") }
        composable(Screen.Settings.route) { PlaceholderScreen("Settings Screen") }
        composable(Screen.Security.route) { PlaceholderScreen("Security Screen") }
    }
}

// Pantalla temporal de placeholder
@Composable
private fun PlaceholderScreen(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 24.sp
        )
    }
}