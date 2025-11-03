package com.example.financeapp.ui.screens.balance

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
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.core.* // For colors, AllTransactions etc.
import com.example.financeApp.R // Your project R class import
import com.example.financeapp.components.* // 💡 FIX: Import all helper functions
import com.example.financeapp.ui.theme.*
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun AccountBalanceScreen(navController: NavController) {
    Scaffold(
        // El color del container principal del Scaffold es el verde de la cabecera
        containerColor = FinWiseGreen,
        bottomBar = { BottomNavBar(navController = navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // --- 1. Top Bar/Header (Fixed Top Area) ---
            BalanceHeader(navController = navController)

            // 2. White Content Area (Transaction List)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(FinWiseWhite)
                    .padding(horizontal = 24.dp)
                    // Offset ajustado para que el mes "March" se vea completo y no se corte
                    .offset(y = (-20).dp)
            ) {
                // Group transactions by month and then sort months
                val groupedTransactions = AllTransactions
                    .groupBy { transaction ->
                        // Extraer el nombre del mes y obtener el objeto Month para ordenar
                        val monthString = transaction.dateAndTime.substringAfter("- ").substringBefore(" ")
                        Month.valueOf(monthString.uppercase(Locale.getDefault()))
                    }
                    .toSortedMap(compareByDescending { it }) // Ordenar los meses de forma descendente (ej: April, March)

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    groupedTransactions.forEach { (monthEnum, transactions) ->
                        // Convertir Month enum a String capitalizado para el UI
                        val monthName = monthEnum.getDisplayName(TextStyle.FULL, Locale.getDefault())
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

                        item { MonthHeader(monthName) }

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

// --- Custom Header Component (Refactorizado para Figma) ---
@Composable
fun BalanceHeader(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        // Custom Top Bar (Back Arrow and Notification)
        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = FinWiseWhite,
                modifier = Modifier.size(28.dp).clickable { navController.popBackStack() }
            )
            Text("Transaction", color = FinWiseWhite, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            NotificationIcon()
        }

        // --- Total Balance Card (Flotante) ---
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            colors = CardDefaults.cardColors(containerColor = FinWiseWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Text(
                "Total Balance",
                color = FinWiseDarkGreen,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )
            Text(
                "$7,783.00",
                color = Void,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }

        // --- Detail Summary Row (AHORA SIN FONDO VERDE OSCURO, usa el FinWiseGreen del Scaffold) ---
        Row(
            modifier = Modifier // Ya no tiene background o clip aquí
                .fillMaxWidth()
                .padding(vertical = 12.dp), // Ajustar el padding vertical para espaciado
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // El padding horizontal se aplica dentro de los BalanceHeaderItem para espaciar
            BalanceHeaderItem(title = "Total Balance", amount = "$7,783.00", iconResId = R.drawable.income_green, isExpense = false)
            Divider(color = FinWiseWhite.copy(alpha = 0.5f), modifier = Modifier.height(40.dp).width(1.dp))
            BalanceHeaderItem(title = "Total Expense", amount = "-$1,187.40", iconResId = R.drawable.expense_green, isExpense = true)
        }

        Spacer(Modifier.height(16.dp))

        // --- Progress Bar ---
        ExpenseProgressIndicator(progress = 0.3f, totalLimit = "$20,000.00", percentage = "30%")

        Spacer(Modifier.height(16.dp))
    }
}
