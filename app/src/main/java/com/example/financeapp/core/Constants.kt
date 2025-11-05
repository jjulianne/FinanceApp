package com.example.financeapp.core


import androidx.compose.ui.graphics.Color
import com.example.financeApp.R
// import com.example.financeapp.ui.theme.LightBlue
// import com.example.financeapp.ui.theme.Void

// You MUST update the 'R' import to your project's R class!
// e.g., import com.example.financeapp.R

// --- Color Definitions ---
val GreenPrimary = Color(0xFF00C1A2)
val BlueExpense = Color(0xFF1E88E5) // <-- Se usará para gastos
val GreyText = Color(0xFF6A6A6A)    // <-- Se usará para texto
val WhiteBackground = Color(0xFFF8F8F8)
val CardBackground = Color(0xFFE8FFF8)

// --- Data Classes ---
data class TransactionItemData(
    val iconResId: Int,
    val title: String,
    val dateAndTime: String,
    val category: String,
    val amount: String,
    val amountColor: Color
)

data class ScreenContent(
    val title: String,
    val totalBalance: String,
    val incomeAmount: String,
    val expenseAmount: String,
    val activeCardColor: Color,
    val passiveCardColor: Color,
    val transactions: List<TransactionItemData>
)

// --- Sample Data (REPLACE R.drawable placeholders with your actual IDs) ---

// Placeholder R class for context (REMOVE THIS BLOCK in your actual project)


val IncomeTransactions = listOf(
    TransactionItemData(R.drawable.salary_white, "Salary", "18:27 - April 30", "Monthly", "$4,000,00", GreyText),
    TransactionItemData(R.drawable.salary_white, "Payments", "17:00 - April 24", "Payments", "$120,00", GreyText),
    TransactionItemData(R.drawable.salary_white, "Salary", "18:39 - March 31", "Monthly", "$4,000,00", GreyText),
    TransactionItemData(R.drawable.salary_white, "Others", "9:30 - March 12", "Upwork", "$340,00", GreyText),
    TransactionItemData(R.drawable.salary_white, "Others", "19:30 - February 20", "Upwork", "$340,00", GreyText),
)

val ExpenseTransactions = listOf(
    TransactionItemData(R.drawable.groceries, "Groceries", "17:00 - April 24", "Pantry", "-$100,00", BlueExpense),
    TransactionItemData(R.drawable.rent, "Rent", "8:30 - April 15", "Rent", "-$674,40", BlueExpense),
    TransactionItemData(R.drawable.transport, "Transport", "7:30 - April 08", "Fuel", "-$4,13", BlueExpense),
    TransactionItemData(R.drawable.food_green, "Food", "19:30 - March 31", "Dinner", "-$70,40", BlueExpense),
    TransactionItemData(R.drawable.rent, "Rent", "18:39 - March 31", "Rent", "-$674,40", BlueExpense),
)

// Combine and sort for the main Transactions view
val AllTransactions = (ExpenseTransactions + IncomeTransactions).sortedByDescending { it.dateAndTime }

// --- Screen Data Definitions ---

// Screen 1: Main Transactions (Income/Expense blocks are neutral)
val TransactionsContent = ScreenContent(
    title = "Transaction",
    totalBalance = "$7,783.00",
    incomeAmount = "$4,120.00",
    expenseAmount = "$1,187.40",
    activeCardColor = GreenPrimary, // Green for Income block text
    passiveCardColor = BlueExpense,  // Blue for Expense block text
    transactions = AllTransactions
)

// Screen 2: Income Filtered (Income block is active)
val IncomeContent = TransactionsContent.copy(
    activeCardColor = GreenPrimary, // Active block uses this color for text/icon
    passiveCardColor = GreenPrimary, // Passive block uses this color for text/icon
    transactions = IncomeTransactions
)

// Screen 3: Expense Filtered (Expense block is active)
val ExpenseContent = TransactionsContent.copy(
    activeCardColor = BlueExpense, // Active block uses this color for text/icon
    passiveCardColor = BlueExpense, // Passive block uses this color for text/icon
    transactions = ExpenseTransactions
)