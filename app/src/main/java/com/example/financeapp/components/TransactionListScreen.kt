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
import com.example.financeapp.ui.theme.* // Importamos los colores de FinWise
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

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
        bottomBar = { BottomNavBar(navController = navController) },
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(FinWiseWhite) // Usamos FinWiseWhite
                    .padding(horizontal = 24.dp) // Aumentamos el padding lateral para el contenido
                    .offset(y = (-30).dp) // Offset para solapar el header
            ) {

                // Group transactions by month and then sort months
                val groupedTransactions = content.transactions
                    .groupBy { transaction ->
                        // Extraer el nombre del mes y obtener el objeto Month para ordenar
                        val monthString = transaction.dateAndTime.substringAfter("- ").substringBefore(" ")
                        // Asumimos que los nombres de los meses en la data están en inglés (e.g., "April")
                        Month.valueOf(monthString.uppercase(Locale.getDefault()))
                    }
                    .toSortedMap(compareByDescending { it }) // Ordenar los meses de forma descendente (ej: April, March)

                // Transactions List
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

// =========================================================================
// HELPER COMPONENTS (Ahora incluye la estructura de tarjetas de Figma)
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
                color = Void, // Usamos Void
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
                iconResId = R.drawable.income_green, // Icono Income
                activeColor = OceanBlue, // <--- Color visual ACTIVO es OceanBlue
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
                activeColor = OceanBlue, // <--- Color visual ACTIVO es OceanBlue
                active = content.activeCardColor == OceanBlue, // <--- Lógica: Activo si la bandera es OceanBlue
                passiveColor = Honeydew,
                modifier = Modifier.weight(1f).clickable(onClick = onExpenseClick)
            )
        }

        // --- Barra de Progreso (Solo en TransactionsScreen/BalanceScreen) ---
        if (showProgressBar) {
            Spacer(Modifier.height(16.dp))
            ExpenseProgressIndicator(
                progress = 0.3f,
                totalLimit = "$20,000.00",
                percentage = "30%"
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}


@Composable
fun SummaryBlockCard(
    title: String, amount: String, iconResId: Int, active: Boolean,
    activeColor: Color, passiveColor: Color, modifier: Modifier = Modifier
) {
    // La lógica de isTransactionScreen ya no es necesaria si el color pasivo siempre es Honeydew.

    val backgroundColor = when {
        // Caso ACTIVO: Usamos el color OceanBlue
        active -> activeColor
        // Caso PASIVO: Usamos Honeydew
        else -> passiveColor
    }

    // Colores de texto e ícono
    // Si está ACTIVO (OceanBlue): Texto/Icono Blanco.
    // Si está PASIVO (Honeydew): Texto/Icono del color activo (OceanBlue)
    val iconTint = if (active) FinWiseWhite else activeColor
    val textColor = if (active) FinWiseWhite else Void
    val amountColor = if (active) FinWiseWhite else activeColor

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.height(110.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp), // Aumentamos padding a 16.dp
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                tint = iconTint,
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
