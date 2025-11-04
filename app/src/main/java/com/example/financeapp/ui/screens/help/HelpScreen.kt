package com.example.financeapp.ui.screens.help

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeApp.R
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun HelpScreen(
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToCustomerService: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    // Estado para controlar qué tab está activo (FAQ o Contact Us)
    var selectedTab by remember { mutableStateOf("FAQ") }

    // Estado para el filtro de categoría en FAQ
    var selectedCategory by remember { mutableStateOf("General") }

    // Estado para el campo de búsqueda
    var searchQuery by remember { mutableStateOf("") }

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
            // Header
            AppHeader(
                title = "Help & FAQS",
                onNavigateBack = onNavigateBack,
                onNotifications = { /* Falta implementar notificaciones */ }
            )

            // Tarjeta blanca de fondo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 135.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(MaterialTheme.colorScheme.surface)
            )

            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 150.dp)
                    .padding(horizontal = 38.dp)
            ) {
                // Titulo "How Can We Help You?"
                Text(
                    text = "How Can We Help You?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Toggle entre FAQ y Contact Us
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TabButton(
                        text = "FAQ",
                        isSelected = selectedTab == "FAQ",
                        onClick = { selectedTab = "FAQ" },
                        modifier = Modifier.weight(1f)
                    )
                    TabButton(
                        text = "Contact Us",
                        isSelected = selectedTab == "Contact Us",
                        onClick = { selectedTab = "Contact Us" },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Contenido según el tab seleccionado
                when (selectedTab) {
                    "FAQ" -> FAQContent(
                        selectedCategory = selectedCategory,
                        onCategoryChange = { selectedCategory = it },
                        searchQuery = searchQuery,
                        onSearchQueryChange = { searchQuery = it }
                    )
                    "Contact Us" -> ContactUsContent(
                        onNavigateToCustomerService = onNavigateToCustomerService
                    )
                }
            }
        }
    }
}

/**
 * Botón de tab personalizado
 */
@Composable
private fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * Contenido de la sección FAQ
 */
@Composable
private fun FAQContent(
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Column {
        // Filtros de categoría
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CategoryChip("General", selectedCategory == "General") { onCategoryChange("General") }
            CategoryChip("Account", selectedCategory == "Account") { onCategoryChange("Account") }
            CategoryChip("Services", selectedCategory == "Services") { onCategoryChange("Services") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de búsqueda
        BasicTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = "Search",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Lista de preguntas FAQ
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(460.dp) // Altura fija para scroll
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FAQItem("How to use FinWise?")
            FAQItem("How much does it cost to use FinWise?")
            FAQItem("How to contact support?")
            FAQItem("How can I reset my password if I forget it?")
            FAQItem("Are there any privacy or data security measures in place?")
            FAQItem("Can I customize settings within the application?")
            FAQItem("How can I delete my account?")
            FAQItem("How do I access my expense history?")
            FAQItem("Can I use the app offline?")
        }
    }
}

/**
 * Chip de categoría
 */
@Composable
private fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(36.dp)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * Item expandible de FAQ
 */
@Composable
private fun FAQItem(question: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                isExpanded = !isExpanded
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = question,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )

            Icon(
                painter = painterResource(
                    id = if (isExpanded) R.drawable.income_white else R.drawable.down_arrow
                ),
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

/**
 * Contenido de la sección Contact Us
 */
@Composable
private fun ContactUsContent(
    onNavigateToCustomerService: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ContactItem(
            R.drawable.help_white,
            "Customer Service",
            onClick = onNavigateToCustomerService
        )
        ContactItem(R.drawable.website, "Website", onClick = { /* Faltaria inventar algun link */ })
        ContactItem(R.drawable.facebook, "Facebook", onClick = { /* Faltaria inventar algun link */ })
        ContactItem(R.drawable.whatsapp, "Whatsapp", onClick = { /* Faltaria inventar algun link */ })
        ContactItem(R.drawable.ig, "Instagram", onClick = { /* Faltaria inventar algun link */ })
    }
}

/**
 * Item de contacto
 */
@Composable
private fun ContactItem(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick() // Llama al callback
                },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = text,
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Navigate",
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun HelpScreenPreview() {
    FinanceAppTheme(darkTheme = false) {
        HelpScreen(darkTheme = false)
    }
}