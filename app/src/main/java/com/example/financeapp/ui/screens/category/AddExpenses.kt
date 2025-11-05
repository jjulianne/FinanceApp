package com.example.financeapp.ui.screens.expense.add_expense

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.navigation.Screen
import com.example.financeapp.ui.screens.category.ExpensesList
import com.example.financeapp.ui.screens.category.sampleExpenses
import com.example.financeapp.ui.theme.Cyprus
import com.example.financeapp.ui.theme.FinWiseGreen
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.TotalBlack
import com.example.financeapp.ui.theme.poppinsFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddExpenseScreen(
    darkTheme: Boolean,
    currentRoute: String = "add_expense_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    Scaffold(
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
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AppHeader(
                title = "Add Expense",
                onNavigateBack = onNavigateBack,
                onNotifications = {}
            )

            // Card blanca
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 135.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                    .background(MaterialTheme.colorScheme.surface)
            )

            AddExpenseContent()
        }
    }
}

@Composable
private fun AddExpenseContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 165.dp)
            .padding(horizontal = 50.dp)
    ) {
        Text("Date", fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFDFF7E2)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "April 30 ,2024",
                fontSize = 15.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                color = TotalBlack,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Text("Category", fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFDFF7E2)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Select the category",
                fontSize = 15.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                color = Cyprus.copy(alpha = 0.5f),
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Text("Amount", fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFDFF7E2)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "$26,00",
                fontSize = 15.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                color = TotalBlack,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))


        Text("Expense Title", fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFDFF7E2)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Dinner",
                fontSize = 15.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                color = TotalBlack,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(166.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFDFF7E2)),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = "Enter message",
                fontFamily = poppinsFamily,
                fontSize = 15.sp,
                color = FinWiseGreen,
                modifier = Modifier.padding(12.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {

            // Botoncitoo
            Button(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 30.dp) // aca cambio la altura
                    .width(180.dp)
                    .height(40.dp),

                shape = RoundedCornerShape(24.dp),

                ) {
                Text(
                    text = "Save",
                    color = TotalBlack,
                    fontFamily = poppinsFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun PreviewAddExpenseScreen() {
    FinanceAppTheme(darkTheme = false) {
        AddExpenseScreen(darkTheme = false)
    }
}
