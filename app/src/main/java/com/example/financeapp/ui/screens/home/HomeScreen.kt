package com.example.financeapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.financeApp.R
import com.example.financeapp.ui.screens.home.components.HomeHeader
import com.example.financeapp.ui.screens.home.components.SavingsAndMetricCard
import com.example.financeapp.ui.screens.home.components.TimeFilterRow
import com.example.financeapp.ui.screens.home.components.TotalStatCard
import com.example.financeapp.ui.screens.home.components.TransactionItemRow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.screens.home.components.ExpenseProgressIndicator
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.components.BottomNavBar
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    darkTheme: Boolean,
    currentRoute: String = "home_route",
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    // Variables de estado del ViewModel
    val totalBalance by viewModel.totalBalance.collectAsStateWithLifecycle()
    val totalExpense by viewModel.totalExpense.collectAsStateWithLifecycle()
    val expenseLimit by viewModel.expenseLimit.collectAsStateWithLifecycle()
    val transactions by viewModel.transactions.collectAsStateWithLifecycle()
    val limitAmount by viewModel.limitAmount.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val savingsGoalProgress by viewModel.savingsGoalProgress.collectAsStateWithLifecycle()
    val savingsIconId by viewModel.savingsIconId.collectAsStateWithLifecycle()
    val metric1Title by viewModel.metric1Title.collectAsStateWithLifecycle()
    val metric1IconId by viewModel.metric1IconId.collectAsStateWithLifecycle()
    val metric1Amount by viewModel.metric1Amount.collectAsStateWithLifecycle()
    val metric2Title by viewModel.metric2Title.collectAsStateWithLifecycle()
    val metric2IconId by viewModel.metric2IconId.collectAsStateWithLifecycle()
    val metric2Amount by viewModel.metric2Amount.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.honeydew), // Fondo base de toda la pantalla
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                darkTheme = darkTheme,
                onNavigateToHome = onNavigateToHome,
                onNavigateToAnalysis = onNavigateToAnalysis,
                onNavigateToTransactions = onNavigateToTransactions,
                onNavigateToCategory = onNavigateToCategory,
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(colorResource(id = R.color.caribbean_green))
            )

            // Contenido principal de la pantalla
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(48.dp),
                    color = colorResource(id = R.color.caribbean_green)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    // Item 1: HomeHeader (Saludo y Notificación)
                    item {
                        HomeHeader()
                    }

                    // Item 2: Bloque de Balances y Barra de Progreso
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorResource(id = R.color.caribbean_green))
                                .padding(horizontal = 24.dp)

                        ) {
                            Spacer(modifier = Modifier.height(8.dp))

                            // Bloques de Balance (TotalStatCard)
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                // 1. Total Balance
                                TotalStatCard(
                                    iconId = R.drawable.income_green,
                                    title = "Total Balance",
                                    amount = totalBalance,
                                    amountColor = colorResource(id = R.color.honeydew),
                                    amountFontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(0.3f)
                                )
                                Spacer(
                                    modifier = Modifier
                                        .padding(horizontal = 40.dp)
                                        .width(2.dp)
                                        .height(50.dp)
                                        .background(colorResource(id = R.color.honeydew))
                                )
                                // 2. Total Expense
                                TotalStatCard(
                                    iconId = R.drawable.expense_green,
                                    title = "Total Expense",
                                    amount = totalExpense,
                                    amountColor = colorResource(id = R.color.ocean_blue),
                                    amountFontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.weight(0.3f)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Barra de Progreso
                            ExpenseProgressIndicator(
                                progress = expenseLimit,
                                limitAmount = limitAmount,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            // Texto de Verificación con icono Checked
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Icono de Checked
                                Icon(
                                    painter = painterResource(id = R.drawable.check_pressed),
                                    contentDescription = "Expense Check",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "${(expenseLimit * 100).toInt()}% Of Your Expenses, Looks Good.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = colorResource(id= R.color.fence_green),
                                    fontSize = 15.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }

                    // Item 3: Tarjeta Central con la Curva
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorResource(id = R.color.caribbean_green))
                                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                                .background(colorResource(id = R.color.honeydew))
                        ) {
                            SavingsAndMetricCard(
                                modifier = Modifier.padding(
                                    top = 35.dp,
                                    bottom = 35.dp
                                ),
                                // Pasamos los parámetros de estado
                                savingsGoalProgress = savingsGoalProgress,
                                savingsIconId = savingsIconId,
                                metric1Title = metric1Title,
                                metric1IconId = metric1IconId,
                                metric1Amount = metric1Amount,
                                metric2Title = metric2Title,
                                metric2IconId = metric2IconId,
                                metric2Amount = metric2Amount,
                            )
                        }
                    }

                    // Item 4: Fila de Filtros
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorResource(id = R.color.honeydew))
                        ) {
                            Column {
                                TimeFilterRow(
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        } }

                    // Item 5: Lista de Transacciones
                    items(transactions, key = { it.id }) { transaction ->
                        TransactionItemRow(item = transaction)
                    }

                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    name = "Home Screen Completa",
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    FinanceAppTheme(darkTheme = false) {
        HomeScreen(
            navController = navController,
            darkTheme = false
        )
    }
}