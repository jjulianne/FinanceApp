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



// Definicion de rutas de navegacion de la aplicacion
sealed class Screen(val route: String) {
    // Rutas de Inicio (Launch)
    object Splash : Screen("splash_route")
    object Onboarding1 : Screen("onboarding1_route")         // Primera pantalla de onboarding
    object Onboarding2 : Screen("onboarding2_route")         // Segunda pantalla de onboarding
    object Welcome : Screen("welcome_route")                 // Pantalla de bienvenida (Log In / Sign Up)
    object Home : Screen("home_route")                       // Pantalla principal

    // Rutas de Autenticación (Auth)
    object Login : Screen("login_route")
    object SignUp : Screen("signup_route")
    object ForgotPassword : Screen("forgot_password_route")
    object NewPassword : Screen("new_password_route")
    object PasswordChangedSuccess : Screen("password_changed_success_route")



    // Rutas de Perfil y Configuración
    object Profile : Screen("profile_route")
    object Settings : Screen("settings_route")
    object Security : Screen("security_route")

    // Rutas de Features principales
    object Transactions : Screen("transactions_route")
    object Categories : Screen("categories_route")
    object SavingGoals : Screen("saving_goals_route")
}

@Composable
fun FinWiseNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Welcome.route
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
                    navController.navigate(Screen.Home.route)
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
                    // Cuando se registra con éxito, podrías llevarlo al home:
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
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


        // Pantallas principales (ejemplo)
        composable(Screen.Home.route) {
            PlaceholderScreen("Home Screen") // Falta la logica
        }

        // Aca van mas pantallas (ejemplo)
        composable(Screen.SavingGoals.route) {
            PlaceholderScreen("Saving Goals Screen") // Falta la logica
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