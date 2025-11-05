package com.example.financeapp.components // Asumo el paquete por la ruta del error

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.core.* // Import data/constants
import com.example.financeApp.R
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.components.ExpenseProgressIndicator
import com.example.financeapp.components.MonthHeader
import com.example.financeapp.components.NotificationIcon
import com.example.financeapp.components.TransactionItem
import com.example.financeapp.ui.theme.* // Importamos los colores de FinWise
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

// =========================================================================
// MAIN REUSABLE SCREEN COMPONENT
// =========================================================================

@Composable
fun TransactionListScreen(
    navController: NavController, // Sigue siendo util para el "Back"
    content: ScreenContent,
    onIncomeClick: () -> Unit, // Navigation callback for Income
    onExpenseClick: () -> Unit, // Navigation callback for Expense

    currentRoute: String,
    darkTheme: Boolean,
    onNavigateToHome: () -> Unit,
    onNavigateToAnalysis: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToProfile: () -> Unit
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
        },
        containerColor = FinWiseGreen // Usamos FinWiseGreen
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
            // ESTE BLOQUE AHORA USA EL DISEÑO Y LA LÓGICA DE ORDENAMIENTO DE ACCOUNTBALANCESCREEN,
            // PERO FILTRA LOS DATOS USANDO content.transactions.
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)) // <-- Cambio de 32dp a 50dp
                    .background(FinWiseWhite)
                    .padding(horizontal = 24.dp)
                    .offset(y = (-20).dp) // <-- Cambio de -30dp a -20dp
            ) {

                // Group transactions by month and then sort months
                val groupedTransactions = content.transactions // <-- FUENTE DE DATOS: content.transactions (Filtra)
                    .groupBy { transaction ->
                        // Extract the month name and get the Month object for ordering
                        val monthString = transaction.dateAndTime.substringAfter("- ").substringBefore(" ")
                        Month.valueOf(monthString.uppercase(Locale.ENGLISH))
                    }
                    .toSortedMap(Comparator.reverseOrder()) // Order months descending (e.g., April, March)

                LazyColumn(
                    // Padding interior del LazyColumn. start = 10.dp lo acerca al borde izquierdo.
                    modifier = Modifier.fillMaxSize().padding(top = 16.dp, start = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    groupedTransactions.forEach { (monthEnum, transactions) ->

                        // 1. Order transactions by date and time (within the month)
                        val sortedTransactions = transactions.sortedByDescending { item ->
                            try {
                                // 1. Construir la cadena de fecha con el año ficticio para el parseo
                                val dateString = item.dateAndTime
                                // Cadena: "HH:mm MMMM d 2025"
                                val datePartWithYear = dateString.substringBefore(" -") + " " + dateString.substringAfter("- ") + " 2025"

                                // 2. Usamos el formato que coincide con la cadena construida
                                val formatter = DateTimeFormatter.ofPattern("HH:mm MMMM d yyyy", Locale.ENGLISH)

                                // 3. Intentamos parsear y retornamos el objeto comparable
                                LocalDateTime.parse(datePartWithYear, formatter)
                            } catch (e: Exception) {
                                // 4. Si el parseo falla (formato incorrecto), retorna la fecha mínima para que vaya al final
                                LocalDateTime.MIN
                            }
                        }

                        // Convertir Month enum to capitalized String for the UI
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


// =========================================================================
// HELPER COMPONENTS (NO MODIFICADO)
// =========================================================================

@Composable
fun CustomHeader(
    navController: NavController,
    content: ScreenContent,
    onIncomeClick: () -> Unit,
    onExpenseClick: () -> Unit
) {
    // Si activeCardColor es Honeydew, asumimos que es la pantalla de Transacciones (con barra de progreso)
    val showProgressBar = content.activeCardColor == Honeydew

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 8.dp, bottom = 16.dp)
    ) {
        // --- Custom Top Bar ---
        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = FinWiseWhite,
                modifier = Modifier.size(28.dp).clickable { navController.popBackStack() }
            )
            Text(content.title, color = FinWiseWhite, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            NotificationIcon() // Icono de campana con fondo blanco
        }

        // --- Total Balance Card ---
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            colors = CardDefaults.cardColors(containerColor = FinWiseWhite), // Fondo blanco
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Text(
                "Total Balance",
                color = FinWiseDarkGreen, // Texto verde oscuro
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )
            Text(
                content.totalBalance,
                // TODO: CORRECCIÓN: Reemplazado 'Void' (que no compilaba). Restaura tu color de texto principal aquí.
                color = MaterialTheme.colorScheme.onSurface, // Usamos Void
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }

        // --- Income / Expense Blocks (Figma Style) ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Income Block
            SummaryBlockCard(
                title = "Income",
                amount = content.incomeAmount,
                iconResId = R.drawable.income_light_green, // Icono Income
                // TODO: CORRECCIÓN: Reemplazado 'OceanBlue' (que no compilaba). Restaura tu color "activo" aquí.
                activeColor = MaterialTheme.colorScheme.primary, // <--- Color visual ACTIVO es OceanBlue
                active = content.activeCardColor == FinWiseGreen, // <--- Lógica: Activo si la bandera es FinWiseGreen
                passiveColor = Honeydew,
                modifier = Modifier.weight(1f).clickable(onClick = onIncomeClick)
            )
            Spacer(Modifier.width(16.dp))
            // Expense Block
            SummaryBlockCard(
                title = "Expense",
                amount = content.expenseAmount,
                iconResId = R.drawable.expense_blue, // Icono Expense
                activeColor = MaterialTheme.colorScheme.primary, // <--- Color visual ACTIVO es OceanBlue
                active = content.activeCardColor == MaterialTheme.colorScheme.primary, // <--- Lógica: Activo si la bandera es OceanBlue
                passiveColor = Honeydew,
                modifier = Modifier.weight(1f).clickable(onClick = onExpenseClick)
            )
        }

        // --- Barra de Progreso (Solo en TransactionsScreen/BalanceScreen) ---

    }
}


@Composable
fun SummaryBlockCard(
    title: String, amount: String, iconResId: Int, active: Boolean,
    activeColor: Color, passiveColor: Color, modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        // Caso ACTIVO: Usamos el color OceanBlue
        active -> activeColor
        // Caso PASIVO: Usamos Honeydew
        else -> passiveColor
    }

    // Colores de texto e ícono
    val textColor = if (active) FinWiseWhite else MaterialTheme.colorScheme.onSurface
    val amountColor = if (active) FinWiseWhite else activeColor

    // Si activo, el ícono es blanco. Si inactivo, usa el color original del drawable (Color.Unspecified).
    val iconTint = if (active) FinWiseWhite else Color.Unspecified

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.height(110.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp), // Aumentamos padding a 16.dp
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                tint = iconTint, // <-- Usa el tint dinámico
                modifier = Modifier.size(25.dp)
            )

            // Text Details
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Esta línea (la del .copy) ahora compilará porque 'textColor' es un Color válido
                Text(title, fontSize = 16.sp, color = textColor.copy(alpha = 0.8f))
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