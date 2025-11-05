package com.example.financeapp.ui.screens.category

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeApp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.navigation.Screen
import com.example.financeapp.ui.theme.FinWiseWhite
import com.example.financeapp.ui.theme.TotalBlack
import com.example.financeapp.ui.theme.buttom
import com.example.financeapp.ui.theme.buttomPressed
import com.example.financeapp.ui.theme.poppinsFamily


data class Expense(
    val id: String,
    val categoryIcon: Int,
    val title: String,
    val time: String,
    val amount: String,
    val month: String
)


val sampleExpenses = listOf(
    Expense("1", R.drawable.food_white, "Dinner", "18:27 - April 30", "-$26,00", "April"),
    Expense("2", R.drawable.food_white, "Delivery Pizza", "15:00 - April 24", "-$18,35", "April"),
    Expense("3", R.drawable.food_white, "Lunch", "12:30 - April 15", "-$15,40", "April"),
    Expense("4", R.drawable.food_white, "Brunch", "9:30 - April 08", "-$12,13", "April"),
    Expense("5", R.drawable.food_white, "Dinner", "20:50 - March 31", "-$27,20", "March")
)

@Composable
fun FoodScreen(
    navController: NavController,
    darkTheme: Boolean,
    currentRoute: String = "category_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            AppHeader(
                title = "Food",
                onNavigateBack = onNavigateBack,
                onNotifications = { }
            )
        },
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
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Top summary section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Balance & Expense Placeholder",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

          // Content card, tarjetita blanca
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 80.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {

                    ExpensesList(
                        expenses = sampleExpenses,
                    )

                    // Botoncitoo
                    Button(
                        onClick = {
                            navController.navigate(Screen.AddExpenses.route)
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 90.dp) // aca cambio la altura
                            .width(180.dp)
                            .height(40.dp),

                        shape = RoundedCornerShape(24.dp),

                    ) {
                        Text(
                            text = "Add Expenses",
                            color = TotalBlack,
                            fontFamily = poppinsFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun ExpensesList(expenses: List<Expense>) {
    val groupedExpenses = expenses.groupBy { it.month }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 20.dp)
    ) {
        groupedExpenses.forEach { (month, items) ->
            item {
                Text(
                    text = month,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFamily,
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
            items(items) { expense ->
                ExpenseItem(expense)
            }
        }
    }
}

@Composable
fun ExpenseItem(expense: Expense) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp), //aca se cambia el espaciadoooooo
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(20.dp))
                .background( color = buttom),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = expense.categoryIcon),
                contentDescription = expense.title,
                modifier = Modifier.size(35.dp),
                tint = FinWiseWhite
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(expense.title, fontFamily = poppinsFamily ,fontWeight = FontWeight.SemiBold)
            Text(expense.time, style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFamily,
                fontSize = 13.sp
            ), color = buttomPressed)
        }

        Text(expense.amount, color = buttomPressed, fontWeight = FontWeight.SemiBold)
    }
}



@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun FoodScreenPreview() {
    val navController = rememberNavController()

    FinanceAppTheme(darkTheme = false) {
        FoodScreen(
            navController = navController,
            darkTheme = false
        )
    }
}