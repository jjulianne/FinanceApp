    // com.example.financeapp/MainActivity.kt

    package com.example.financeapp

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import com.example.financeapp.navigation.FinWiseNavigation
    import com.example.financeapp.navigation.Screen // 💡 FIX: Ensure this import exists
    import com.example.financeapp.ui.theme.FinanceAppTheme
    import dagger.hilt.android.AndroidEntryPoint

    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                FinanceAppTheme {
                    // 💡 FIX: Set the startDestination to Screen.Home.route,
                    // which is your AccountBalanceScreen/Home route.
                    FinWiseNavigation(startDestination = Screen.Home.route)
                }
            }
        }
    }