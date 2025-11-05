package com.example.financeapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.financeapp.ui.screens.launch.Onboarding1Screen
import com.example.financeapp.ui.screens.launch.Onboarding2Screen
import com.example.financeapp.ui.screens.launch.WelcomeScreen
import com.example.financeapp.ui.screens.launch.SplashScreen
import com.example.financeapp.ui.screens.auth.LoginScreen
import com.example.financeapp.ui.screens.auth.SignUpScreen
import com.example.financeapp.ui.screens.auth.ForgotPasswordScreen
import com.example.financeapp.ui.screens.auth.NewPasswordScreen
import com.example.financeapp.ui.screens.auth.PasswordChangedSuccessScreen
import com.example.financeapp.ui.screens.auth.SecurityPinScreen
import com.example.financeapp.ui.screens.home.HomeScreen
import com.example.financeapp.ui.screens.profile.ProfileScreen
import com.example.financeapp.ui.screens.profile.edit_profile.EditProfileScreen
import com.example.financeapp.ui.screens.settings.SettingsScreen
import com.example.financeapp.ui.screens.settings.NotificationSettingsScreen
import com.example.financeapp.ui.screens.settings.PasswordSettingsScreen
import com.example.financeapp.ui.screens.settings.DeleteAccountScreen
import com.example.financeapp.ui.screens.help.HelpScreen
import com.example.financeapp.ui.screens.help.OnlineSupportScreen
import com.example.financeapp.ui.screens.help.SupportChatScreen
import com.example.financeapp.ui.screens.security.AddFingerprintScreen
import com.example.financeapp.ui.screens.security.SecurityScreen
import com.example.financeapp.ui.screens.security.ChangePinScreen
import com.example.financeapp.ui.screens.security.FingerprintScreen
import com.example.financeapp.ui.screens.security.FingerprintDetailScreen
import com.example.financeapp.ui.screens.security.TermsAndConditionsScreen
import com.example.financeapp.ui.screens.balance.AccountBalanceScreen
import com.example.financeapp.ui.screens.transactions.TransactionsScreen
import com.example.financeapp.ui.screens.transactions.IncomeScreen
import com.example.financeapp.ui.screens.transactions.ExpenseScreen


// Definicion de rutas de navegacion de la aplicacion
sealed class Screen(val route: String) {
    // Rutas de Inicio (Launch)
    object Splash : Screen("splash_route")
    object Onboarding1 : Screen("onboarding1_route")         // Primera pantalla de onboarding
    object Onboarding2 : Screen("onboarding2_route")         // Segunda pantalla de onboarding
    object Welcome : Screen("welcome_route")                 // Pantalla de bienvenida (Log In / Sign Up)
    object Home : Screen("home_route")
    object Logout : Screen("logout_route")
// Pantalla principal

    // Rutas de Autenticación (Auth)
    object Login : Screen("login_route")
    object SignUp : Screen("signup_route")
    object ForgotPassword : Screen("forgot_password_route")
    object NewPassword : Screen("new_password_route")
    object PasswordChangedSuccess : Screen("password_changed_success_route")





    // Rutas de Perfil y Configuración
    object Profile : Screen("profile_route")
    object EditProfile : Screen("edit_profile_route")
    object Security : Screen("security_route")
    object ChangePin : Screen("change_pin_route")                   // Cambiar PIN
    object Fingerprint : Screen("fingerprint_route")                // Configurar huella
    object TermsAndConditions : Screen("terms_conditions_route")    // Términos y condiciones
    object Settings : Screen("settings_route")                      // Configuracion general
    object NotificationSettings : Screen("notification_settings_route")  // Configuracion de notificaciones
    object PasswordSettings : Screen("password_settings_route")          // Cambiar contraseña
    object DeleteAccount : Screen("delete_account_route")                // Eliminar cuenta

    // Usamos "{fingerprintName}" para pasar el nombre como argumento en la URL.
    object FingerprintDetail : Screen("fingerprint_detail_route/{fingerprintName}") {
        // Funcion helper para construir la ruta con el argumento
        fun withArgs(name: String): String {
            return "fingerprint_detail_route/$name"
        }
    }
    object AddFingerprint : Screen("add_fingerprint_route")         // Añadir huella


    // Rutas de Features principales
    object Analysis : Screen("analysis_route")
    object Transactions : Screen("transactions_route")
    object Income : Screen("income_route")
    object Expense : Screen("expense_route")
    object Category : Screen("category_route")
    object SavingGoals : Screen("saving_goals_route")

    object Help : Screen("help_route")
    object OnlineSupport : Screen("online_support_route")
    object SupportChat : Screen("support_chat_route/{chatId}") {
        fun withArgs(chatId: String): String {
            return "support_chat_route/$chatId"
        }
    }
}

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun FinWiseNavigation(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {



        // Pantalla Splash (inicial)
        composable(Screen.Splash.route) {
            // El SplashScreen decide el destino segun el estado de autenticacion:
            // - Home (si ya esta autenticado)
            // - Onboarding (si es la primera vez)
            // - Login (si no esta autenticado, pero ya vio onboarding)
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

        // Pantalla Onboarding 1 (Welcome To Expense Manager)
        composable(Screen.Onboarding1.route) {
            Onboarding1Screen(
                onNext = {
                    navController.navigate(Screen.Onboarding2.route)
                }
            )
        }

        // Pantalla Onboarding 2 (Are You Ready To Take Control Of Your Finances?)
        composable(Screen.Onboarding2.route) {
            Onboarding2Screen(
                onNext = {
                    navController.navigate(Screen.Welcome.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // Pantalla Welcome (Log In / Sign Up)
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(Screen.ForgotPassword.route)
                }
            )
        }
        // Rutas de Autenticacion

        //Login
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToForgotPassword = {
                    navController.navigate(Screen.ForgotPassword.route)
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
                onLogin = {
                    navController.navigate(Screen.Home.route) {
                        // Primero se limpia  el stack de navegacion para que Home sea la nueva raiz
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        // Aseguramos que solo haya una instancia de Home
                        launchSingleTop = true
                    }
                }
            )
        }

        //   pantalla de registro
        composable(Screen.SignUp.route) {
            SignUpScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onSignUp = {
                    navController.navigate(Screen.Home.route) {
                        // Primero se limpia  el stack de navegacion para que Home sea la nueva raiz
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        // Aseguramos que solo haya una instancia de Home
                        launchSingleTop = true
                    }
                }
            )
        }

// Pantalla Forgot Password
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onResetPassword = {
                    // 👇 Cuando el usuario toca "Next step", lo mandamos al PIN screen
                    navController.navigate(Screen.Security.route)
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }
        // Pantalla Security PIN
        composable(Screen.Security.route) {
            SecurityPinScreen(
                onAccept = {
                    navController.navigate(Screen.NewPassword.route)
                },
                onResend = {
                    // Podrías mostrar un snackbar o repetir la acción de enviar PIN
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }
// Pantalla New Password
        composable(Screen.NewPassword.route) {
            NewPasswordScreen(
                onChangePassword = {
                    navController.navigate(Screen.PasswordChangedSuccess.route)
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

// Pantalla de confirmación de cambio de contraseña
        composable(Screen.PasswordChangedSuccess.route) {
            PasswordChangedSuccessScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.PasswordChangedSuccess.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.PasswordChangedSuccess.route) {
            PasswordChangedSuccessScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.PasswordChangedSuccess.route) { inclusive = true }
                    }
                }
            )
        }



        // Pantallas principales (ejemplo)
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                darkTheme = isDarkTheme,
                currentRoute = Screen.Home.route,

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(
                            navController.graph.startDestinationRoute!!
                        ) { saveState = true }
                    }
                },
                onNavigateToAnalysis = {
                    navController.navigate(Screen.Analysis.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToTransactions = {
                    navController.navigate(Screen.Transactions.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToCategory = {
                    navController.navigate(Screen.Category.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                currentRoute = Screen.Profile.route,
                darkTheme = isDarkTheme,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToEditProfile = { navController.navigate(route = Screen.EditProfile.route) },
                onNavigateToSecurity = { navController.navigate(Screen.Security.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) },
                onNavigateToHelp = { navController.navigate(Screen.Help.route) },
                onLogout = { /* Falta implementar */ },

                // Callbacks del BottomNavBar
                // (Usamos popUpTo para evitar apilar pantallas)
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToAnalysis = {
                    navController.navigate(Screen.Analysis.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToTransactions = {
                    navController.navigate(Screen.Transactions.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToCategory = {
                    navController.navigate(Screen.Category.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToProfile = { /* Ya estás aquí */ }
            )
        }

        composable(Screen.EditProfile.route) {
            EditProfileScreen(
                currentRoute = Screen.Profile.route, // Mantenemos el ícono de perfil seleccionado
                darkTheme = isDarkTheme,
                onThemeChange = onThemeChange,
                onNavigateBack = { navController.popBackStack() },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToAnalysis = {
                    navController.navigate(Screen.Analysis.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToTransactions = {
                    navController.navigate(Screen.Transactions.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToCategory = {
                    navController.navigate(Screen.Category.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToProfile = { navController.popBackStack() }
            )
        }

        // Pantalla Settings (Configuracion general)
        composable(Screen.Settings.route) {
            SettingsScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },
                onNavigateToNotificationSettings = {
                    navController.navigate(Screen.NotificationSettings.route)
                },
                onNavigateToPasswordSettings = {
                    navController.navigate(Screen.PasswordSettings.route)
                },
                onNavigateToDeleteAccount = {
                    navController.navigate(Screen.DeleteAccount.route)
                },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        // Pantalla Notification Settings
        composable(Screen.NotificationSettings.route) {
            NotificationSettingsScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        // Pantalla Password Settings
        composable(Screen.PasswordSettings.route) {
            PasswordSettingsScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        // Pantalla Delete Account
        composable(Screen.DeleteAccount.route) {
            DeleteAccountScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Help.route) {
            HelpScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route,
                onNavigateBack = { navController.popBackStack() },

                onNavigateToCustomerService = {
                    navController.navigate(Screen.OnlineSupport.route)
                },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.OnlineSupport.route) {
            OnlineSupportScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado
                onNavigateBack = { navController.popBackStack() },

                onNavigateToChat = { chatId ->
                    navController.navigate(Screen.SupportChat.withArgs(chatId))
                },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(
            route = Screen.SupportChat.route, // Usa la plantilla "support_chat_route/{chatId}"
            arguments = listOf(
                navArgument("chatId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            SupportChatScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado
                onNavigateBack = { navController.popBackStack() },

                // Callbacks del BottomNavBar
                onNavigateToHome = { navController.navigate(Screen.Home.route) { popUpTo(navController.graph.startDestinationRoute!!) { saveState = true } } },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }


        composable(Screen.Security.route) {
            SecurityScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },
                onNavigateToChangePin = {
                    navController.navigate(Screen.ChangePin.route)
                },
                onNavigateToFingerprint = {
                    navController.navigate(Screen.Fingerprint.route)
                },
                onNavigateToTermsAndConditions = {
                    navController.navigate(Screen.TermsAndConditions.route)
                },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        // Pantalla Change Pin
        composable(Screen.ChangePin.route) {
            ChangePinScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        // Pantalla Fingerprint
        composable(Screen.Fingerprint.route) {
            FingerprintScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },
                onViewFingerprint = {
                        fingerprintName -> navController.navigate(Screen.FingerprintDetail.withArgs(fingerprintName))
                },
                onAddFingerprint = {
                    navController.navigate(Screen.AddFingerprint.route)
                },

                // CallVbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(
            route = Screen.FingerprintDetail.route,
            arguments = listOf(navArgument("fingerprintName") { type = NavType.StringType })
        ) {
            // El ViewModel se inyecta automaticamente usando hilt
            // y obtiene 'fingerprintName' del SavedStateHandle.

            FingerprintDetailScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },

                // El ViewModel se encarga de la logica y nos avisa
                // cuando debe navegar hacia atras
                onDeleteSuccess = {
                    navController.popBackStack()
                },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.AddFingerprint.route) {
            AddFingerprintScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        // Pantalla Terms And Conditions
        composable(Screen.TermsAndConditions.route) {
            TermsAndConditionsScreen(
                darkTheme = isDarkTheme,
                currentRoute = Screen.Profile.route, // Mantiene Profile seleccionado en navbar
                onNavigateBack = { navController.popBackStack() },
                onAccept = {
                    // Falta implementar
                    navController.popBackStack()
                },

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                    }
                },
                onNavigateToAnalysis = { /* Falta implementar */ },
                onNavigateToTransactions = { /* Falta implementar */ },
                onNavigateToCategory = { /* Falta implementar */ },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }
        // Aca van mas pantallas (ejemplo)
        composable(Screen.SavingGoals.route) {
            PlaceholderScreen("Saving Goals Screen") // Falta la logica
        }

        composable(Screen.Analysis.route) {
            // Asumo que tu 'AccountBalanceScreen' es la pantalla de 'Analysis'
            AccountBalanceScreen(
                navController = navController,
                darkTheme = isDarkTheme,
                currentRoute = Screen.Analysis.route,

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToAnalysis = { /* Ya estás aquí */ },
                onNavigateToTransactions = {
                    navController.navigate(Screen.Transactions.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToCategory = {
                    navController.navigate(Screen.Category.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screen.Transactions.route) {
            TransactionsScreen(
                navController = navController,
                darkTheme = isDarkTheme,
                currentRoute = Screen.Transactions.route,

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToAnalysis = {
                    navController.navigate(Screen.Analysis.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToTransactions = { /* Ya estás aquí */ },
                onNavigateToCategory = {
                    navController.navigate(Screen.Category.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screen.Income.route) {
            IncomeScreen(
                navController = navController,
                darkTheme = isDarkTheme,
                currentRoute = Screen.Transactions.route, // Mantenemos Transactions seleccionado

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToAnalysis = {
                    navController.navigate(Screen.Analysis.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToTransactions = {
                    navController.navigate(Screen.Transactions.route) { // Vuelve a la lista principal
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToCategory = {
                    navController.navigate(Screen.Category.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screen.Expense.route) {
            ExpenseScreen(
                navController = navController,
                darkTheme = isDarkTheme,
                currentRoute = Screen.Transactions.route, // Mantenemos Transactions seleccionado

                // Callbacks del BottomNavBar
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToAnalysis = {
                    navController.navigate(Screen.Analysis.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToTransactions = {
                    navController.navigate(Screen.Transactions.route) { // Vuelve a la lista principal
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToCategory = {
                    navController.navigate(Screen.Category.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screen.Category.route) {
            PlaceholderScreen("Category Screen") // Falta la logica
        }

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