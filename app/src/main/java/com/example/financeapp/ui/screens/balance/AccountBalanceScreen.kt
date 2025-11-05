package com.example.financeapp.ui.screens.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.core.* // For colors, AllTransactions etc.
// --- CORRECCIÓN 1: Import del paquete R (minúscula) ---
import com.example.financeApp.R
import com.example.financeapp.components.* // 💡 FIX: Import all helper functions

import com.example.financeapp.ui.theme.*
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

import androidx.navigation.compose.rememberNavController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AccountBalanceScreen(
    navController: NavController,
    // --- CORRECCIÓN 2: Añadidos parámetros para BottomNavBar ---
    darkTheme: Boolean,
    currentRoute: String,
    onNavigateToHome: () -> Unit,
    onNavigateToAnalysis: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToProfile: () -> Unit
    // --- FIN CORRECCIÓN 2 ---
) {
    Scaffold(
        // The main container color of the Scaffold is the header green
        containerColor = FinWiseGreen,
        // --- CORRECCIÓN 3: Pasamos los nuevos parámetros al BottomNavBar ---
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
        // --- FIN CORRECCIÓN 3 ---
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
                    .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                    .background(FinWiseWhite)
                    .padding(horizontal = 24.dp)
                    // Offset adjusted so the month "March" is fully visible and not cut
                    .offset(y = (-20).dp)
            ) {
                // Group transactions by month and then sort months
                val groupedTransactions = AllTransactions
                    .groupBy { transaction ->
                        // Extract the month name and get the Month object for ordering
                        val monthString = transaction.dateAndTime.substringAfter("- ").substringBefore(" ")
                        Month.valueOf(monthString.uppercase(Locale.ENGLISH))
                    }
                    .toSortedMap(Comparator.reverseOrder()) // Order months descending (e.g., April, March)

                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(start = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    groupedTransactions.forEach { (monthEnum, transactions) ->

                        // 1. Order transactions by date and time (within the month)
                        val sortedTransactions = transactions.sortedByDescending { item ->
                            try {
                                // 1. Extraemos SOLO la sub-cadena que contiene HORA y FECHA.
                                // Si item.dateAndTime es "18:27 - April 30", la cadena de entrada es esa.
                                val dateString = item.dateAndTime

                                // 2. AÑADIMOS EL GUION LITERAL Y EL AÑO AL FORMATO para que coincida.
                                // Usamos un año ficticio (2025) ya que solo necesitamos ordenar por día/hora.
                                // Y asumimos el día de la transacción (item.dateAndTime)
                                val formatter = DateTimeFormatter.ofPattern("HH:mm - MMMM d yyyy", Locale.ENGLISH)

                                // 3. Modificamos la cadena de entrada para añadir el año
                                val datePartWithYear = dateString + " 2025"

                                // 4. Intentamos parsear y retornar el objeto comparable
                                LocalDateTime.parse(datePartWithYear, formatter)
                            } catch (e: Exception) {
                                // Si el parseo falla (formato incorrecto), esto garantiza que la transacción va al final
                                LocalDateTime.MIN
                            }
                        }

                        // Convert Month enum to capitalized String for the UI
                        val monthName = monthEnum.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }

                        item { MonthHeader(monthName) }

                        items(sortedTransactions) { item ->
                            TransactionItem(
                                iconResId = item.iconResId,
                                title = item.title,
                                dateAndTime = item.dateAndTime,
                                category = item.category,
                                amount = item.amount,
                                amountColor = item.amountColor,
                                onClick = {}
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
                // --- CORRECCIÓN 4: 'Void' no existe, usamos un color temporal ---
                // TODO: Reemplaza esto con tu nuevo color de texto principal (ej. GreyText)
                color = MaterialTheme.colorScheme.onSurface, // Era Void
                // --- FIN CORRECCIÓN 4 ---
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )
            Text(
                "$7,783.00",
                // --- CORRECCIÓN 5: 'Void' no existe, usamos un color temporal ---
                // TODO: Reemplaza esto con tu nuevo color de texto principal (ej. GreyText)
                color = MaterialTheme.colorScheme.onSurface, // Era Void
                // --- FIN CORRECCIÓN 5 ---
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }

        // --- Detail Summary Row (AHORA SIN FONDO VERDE OSCURO, usa el FinWiseGreen del Scaffold) ---
        Row(
            modifier = Modifier // Ya no tiene background o clip aquí
                .fillMaxWidth()
                .padding(vertical = 12.dp), // Ajustar el padding vertical para espaciado
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // El padding horizontal se aplica dentro de los BalanceHeaderItem para espaciar
            BalanceHeaderItem(title = "Total Balance", amount = "$7,783.00", iconResId = R.drawable.income_green, isExpense = false)
            Divider(color = Honeydew.copy(alpha = 0.5f), modifier = Modifier.height(40.dp).width(1.dp))
            BalanceHeaderItem(title = "Total Expense", amount = "-$1,187.40", iconResId = R.drawable.expense_green, isExpense = true)
        }

        Spacer(Modifier.height(16.dp))

        // --- Progress Bar ---
        ExpenseProgressIndicator(progress = 0.3f, totalLimit = "$20,000.00", percentage = "30%")

        Spacer(Modifier.height(16.dp))
    }
}



// (Añadir dentro del mismo archivo donde está AccountBalanceScreen)


@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun AccountBalanceScreenPreview() {
    // --- CORRECCIÓN 6: El Preview también necesita los nuevos parámetros ---
    AccountBalanceScreen(
        navController = rememberNavController(),
        darkTheme = false,
        currentRoute = "balance_route", // Ruta de ejemplo para el preview
        onNavigateToHome = {},
        onNavigateToAnalysis = {},
        onNavigateToTransactions = {},
        onNavigateToCategory = {},
        onNavigateToProfile = {}
    )
    // --- FIN CORRECCIÓN 6 ---
}