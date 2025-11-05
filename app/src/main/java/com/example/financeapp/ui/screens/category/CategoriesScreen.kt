package com.example.financeapp.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.buttom
import com.example.financeapp.ui.theme.buttomPressed
import com.example.financeApp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.theme.FinWiseWhite
import com.example.financeapp.ui.theme.poppinsFamily


data class Category(
    val id: String,
    val name: String,
    val iconResId: Int
)


val sampleCategories = listOf(
    Category("food", "Food", R.drawable.food_white ),
    Category("transport", "Transport", R.drawable.transport),
    Category("medicine", "Medicine", R.drawable.medicine),
    Category("groceries", "Groceries", R.drawable.groceries),
    Category("rent", "Rent", R.drawable.rent),
    Category("gifts", "Gifts", R.drawable.gift),
    Category("savings", "Savings", R.drawable.savings),
    Category("entertainment", "Entertainment", R.drawable.entertainment),
    Category("more", "More", R.drawable.more)
)




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    navController: NavController,
    darkTheme: Boolean,
    currentRoute: String = "category_route",
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            AppHeader(
                title = "Categories",
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

            // Content card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 80.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))

                    .background(MaterialTheme.colorScheme.surface)
            )
 {
             CategoryGrid(categories = sampleCategories) { category ->
                 navController.navigate(category.id)
             }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onClick: (Category) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        // Cuadro
        Box(
            modifier = Modifier
                .size(105.dp) // tamaño del cuadrado
                .background(
                    color = if (isPressed.value) buttomPressed else buttom,
                    shape = RoundedCornerShape(26.dp)
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onClick(category) }
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = category.iconResId),
                contentDescription = category.name,
                modifier = Modifier.size(50.dp),
                tint = FinWiseWhite
            )
        }

        // Texto debajo
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFamily,
                fontSize = 15.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@Composable
fun CategoryGrid(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                onClick = { onCategoryClick(category) }
            )
        }
    }
}



@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun CategoriesScreenPreview() {
    FinanceAppTheme(darkTheme = false) {
        CategoriesScreen(
            navController = rememberNavController(),
            darkTheme = false
        )
    }
}