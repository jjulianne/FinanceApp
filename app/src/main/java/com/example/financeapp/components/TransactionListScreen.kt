package com.example.financeapp.components



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.financeapp.core.* // Import data/constants
import com.example.financeApp.R
// You'll need to define your BottomNavBar logic separately if it's external

// =========================================================================
// MAIN REUSABLE SCREEN COMPONENT
// =========================================================================

@Composable
fun TransactionListScreen(
    navController: NavController,
    content: ScreenContent,
    onIncomeClick: () -> Unit, // Navigation callback for Income
    onExpenseClick: () -> Unit // Navigation callback for Expense
) {
    Scaffold(
        // Assuming BottomNavBar is here, uncomment when ready:
        bottomBar = { BottomNavBar(navController = navController) },
        containerColor = GreenPrimary
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // --- 1. Top Bar/Header (Fixed Top Area) ---
            CustomHeader(
                navController = navController,
                content = content,
                onIncomeClick = onIncomeClick,
                onExpenseClick = onExpenseClick
            )

            // 2. White Content Area (Transaction List)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(WhiteBackground)
                    .padding(horizontal = 16.dp)
                    .offset(y = (-20).dp) // Adjust based on image
            ) {

                Spacer(Modifier.height(16.dp))

                // Group transactions by month
                val groupedTransactions = content.transactions.groupBy {
                    // Simple month extraction (e.g., "April")
                    it.dateAndTime.substringAfter("- ").substringBefore(" ")
                }

                // Transactions List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    groupedTransactions.forEach { (month, transactions) ->
                        item { MonthHeader(month) }

                        items(transactions) { item ->
                            TransactionItem(
                                iconResId = item.iconResId,
                                title = item.title,
                                dateAndTime = item.dateAndTime,
                                category = item.category,
                                amount = item.amount,
                                amountColor = item.amountColor
                            )
                        }
                    }
                }
            }
        }
    }
}

// =========================================================================
// HELPER COMPONENTS
// =========================================================================

@Composable
fun CustomHeader(
    navController: NavController,
    content: ScreenContent,
    onIncomeClick: () -> Unit,
    onExpenseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        // --- Custom Top Bar ---
        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White,
                modifier = Modifier.size(28.dp).clickable { navController.popBackStack() }
            )
            Text(content.title, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Icon(painterResource(id = R.drawable.notif_green), contentDescription = "Notifications", tint = Color.White, modifier = Modifier.size(24.dp))
        }

        // --- Total Balance Card ---
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Text(
                "Total Balance",
                color = GreyText,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            Text(
                content.totalBalance,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
        }

        // --- Income / Expense Blocks (Clickable) ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Income Block
            SummaryBlockCard(
                title = "Income", amount = content.incomeAmount, iconResId = R.drawable.income_light_green,
                // If Income is GreenPrimary, it's the active (white background) card
                active = content.activeCardColor == GreenPrimary,
                activeColor = content.activeCardColor, passiveColor = content.passiveCardColor,
                modifier = Modifier.weight(1f).clickable(onClick = onIncomeClick)
            )
            Spacer(Modifier.width(16.dp))
            // Expense Block
            SummaryBlockCard(
                title = "Expense", amount = content.expenseAmount, iconResId = R.drawable.expense_blue,
                // If Expense is BlueExpense, it's the active (white background) card
                active = content.activeCardColor == BlueExpense,
                activeColor = content.activeCardColor, passiveColor = content.passiveCardColor,
                modifier = Modifier.weight(1f).clickable(onClick = onExpenseClick)
            )
        }
    }
}

@Composable
fun SummaryBlockCard(
    title: String, amount: String, iconResId: Int, active: Boolean,
    activeColor: Color, passiveColor: Color, modifier: Modifier = Modifier
) {
    // Determine the color scheme based on the active state
    val blockColor = if (active) activeColor else Color.White
    val textColor = if (active) Color.Black else Color.White
    val amountColor = if (active) activeColor else Color.White
    val backgroundColor = if (active) Color.White else blockColor.copy(alpha = 0.5f)

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.height(110.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                tint = blockColor,
                modifier = Modifier.size(24.dp)
            )

            // Text Details
            Column(horizontalAlignment = Alignment.Start) {
                Text(title, fontSize = 14.sp, color = textColor.copy(alpha = 0.8f))
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
fun MonthHeader(month: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(month, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        Icon(
            painterResource(id = R.drawable.calendar),
            contentDescription = "Calendar",
            tint = GreenPrimary,
            modifier = Modifier.size(24.dp)
        )
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
            // Icon Column
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