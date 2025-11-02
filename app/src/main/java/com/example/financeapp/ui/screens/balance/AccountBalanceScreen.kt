package com.example.financeapp.ui.screens.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeApp.R
import com.example.financeapp.components.BottomNavBar
// --- Color Definitions (Based on Image) ---
val GreenPrimary = Color(0xFF00C1A2)
val GreenSecondary = Color(0xFF00E676)
val BlueExpense = Color(0xFF1E88E5)
val GreyText = Color(0xFF6A6A6A)
val WhiteBackground = Color(0xFFF8F8F8) // Light grey background
val CardBackground = Color(0xFFE8FFF8) // Very light green/white for small cards

// --- Placeholder R Class (REPLACE WITH YOUR ACTUAL R CLASS IMPORTS) ---
// Note: You must remove this object definition if you import your actual R class.


// =========================================================================
// MAIN SCREEN COMPOSABLE
// =========================================================================

@Composable
fun AccountBalanceScreen(navController: NavController) {
    Scaffold(
        bottomBar = {  BottomNavBar(navController = navController)  },
        containerColor = GreenPrimary // Main screen background is solid green
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // 1. Top Bar/Header (Green Background Area)
            CustomHeader(navController)

            // 2. White Content Area (Rounded Top)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(WhiteBackground)
                    .padding(horizontal = 16.dp)
                    .offset(y = (-10).dp) // Slight overlap
            ) {

                Spacer(Modifier.height(16.dp))

                // Transactions Header
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Transactions", fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = Color.Black)
                    Text("See all", color = GreenPrimary, fontSize = 14.sp, modifier = Modifier.clickable { /* Handle "See all" click */ })
                }

                // Transactions List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item { TransactionItem(R.drawable.salary_green, "Salary", "18:27 - April 30", "Monthly", "$4,000,00", GreenPrimary) }
                    item { TransactionItem(R.drawable.groceries, "Groceries", "17:00 - April 24", "Pantry", "-$100,00", BlueExpense) }
                    item { TransactionItem(R.drawable.rent, "Rent", "8:30 - April 15", "Rent", "-$674,40", BlueExpense) }
                    item { TransactionItem(R.drawable.transport, "Transport", "9:30 - April 08", "Fuel", "-$4,13", BlueExpense) }
                }
            }
        }
    }
}

// =========================================================================
// HEADER COMPONENTS
// =========================================================================

@Composable
fun CustomHeader(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        // Custom Top Bar (Back Arrow and Notification)
        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(28.dp).clickable { navController.popBackStack() }
            )
            Text(
                "Account Balance",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Icon(
                painterResource(id = R.drawable.notif_green),
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        // Total Balance and Expense
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BalanceHeaderItem(title = "Total Balance", amount = "$7,783.00", iconResId = R.drawable.income_green, isExpense = false)
            Divider(color = Color.White.copy(alpha = 0.5f), modifier = Modifier.height(40.dp).width(1.dp))
            BalanceHeaderItem(title = "Total Expense", amount = "-$1,187.40", iconResId = R.drawable.expense_green, isExpense = true)
        }

        // Progress Bar
        ExpenseProgressIndicator(progress = 0.3f, totalLimit = "$20,000.00", percentage = "30%")

        Spacer(Modifier.height(20.dp))

        // Income / Expense Blocks
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SummaryBlockCard(
                title = "Income", amount = "$4,000.00", iconResId = R.drawable.income_light_green,
                iconColor = GreenPrimary, amountColor = Color.Black, modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(16.dp))
            SummaryBlockCard(
                title = "Expense", amount = "$1,187.40", iconResId = R.drawable.expense_green,
                iconColor = BlueExpense, amountColor = BlueExpense, modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(16.dp))

        // Expense Message
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painterResource(id = android.R.drawable.checkbox_on_background), // Placeholder for checkmark box icon
                contentDescription = "Good",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("30% Of Your Expenses, Looks Good.", color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun BalanceHeaderItem(title: String, amount: String, iconResId: Int, isExpense: Boolean) {
    Column(modifier = Modifier.width(140.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painterResource(id = iconResId),
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(title, color = Color.White.copy(0.7f), fontSize = 14.sp)
        }
        Text(
            amount,
            color = if (isExpense) Color(0xFFB3E5FC) else Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }
}

@Composable
fun ExpenseProgressIndicator(progress: Float, totalLimit: String, percentage: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.3f))
    ) {
        // Actual Progress (Dark part is rounded)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black.copy(alpha = 0.9f))
        )

        // Text Overlay
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(percentage, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(totalLimit, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }
    }
}

// =========================================================================
// MIDDLE CARDS AND TRANSACTION COMPONENTS
// =========================================================================

@Composable
fun SummaryBlockCard(
    title: String, amount: String, iconResId: Int, iconColor: Color,
    amountColor: Color, modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.height(110.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon Box
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Text Details
            Column(horizontalAlignment = Alignment.Start) {
                Text(title, fontSize = 14.sp, color = Color.Black.copy(alpha = 0.8f))
                Text(
                    amount,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = amountColor
                )
            }
        }
    }
}

@Composable
fun TransactionItem(
    iconResId: Int, title: String, dateAndTime: String, category: String, amount: String, amountColor: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Column (Blue background)
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(BlueExpense.copy(alpha = 0.1f))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = iconResId),
                    contentDescription = title,
                    tint = BlueExpense,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(Modifier.width(16.dp))

            // Details Column (Title and Date)
            Column(modifier = Modifier.width(120.dp)) {
                Text(title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(dateAndTime, fontSize = 12.sp, color = BlueExpense)
            }

            // Category Column (Separator with Text)
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(
                    color = BlueExpense.copy(alpha = 0.2f),
                    modifier = Modifier.height(30.dp).width(1.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(category, fontSize = 14.sp, color = GreyText, textAlign = TextAlign.Center)
                Spacer(Modifier.width(12.dp))
            }

            // Amount Column
            Text(
                amount,
                color = amountColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.End
            )
        }
    }
}