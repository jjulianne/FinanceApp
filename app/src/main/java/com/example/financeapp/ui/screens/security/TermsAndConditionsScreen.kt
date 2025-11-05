package com.example.financeapp.ui.screens.security

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.components.AppHeader
import com.example.financeapp.components.BottomNavBar
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun TermsAndConditionsScreen(
    darkTheme: Boolean,
    currentRoute: String = "profile_route",
    onNavigateBack: () -> Unit = {},
    onAccept: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    var isAccepted by remember { mutableStateOf(false) }

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
                title = "Terms And Conditions",
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

            // Contenido scrolleable
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 155.dp)
                    .padding(horizontal = 38.dp)
                    .padding(bottom = 140.dp) // Espacio para el navbar
            ) {
                // Titulo principal
                Text(
                    text = "Est Fugiat Assumenda Aut Reprehenderit",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Contenido scrolleable
                Column(
                    modifier = Modifier
                        .weight(1f, fill = false)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Parrafo principal
                    Text(
                        text = "Lorem ipsum dolor sit amet. Et odio officia aut voluptate internos est omnis vitae ut architecto sunt non tenetur fuga ut provident vero. Quo aspernatur facere et consectetur ipsum et facere corrupti est aspernatur facere. Est fugiat assumenda aut reprehenderit voluptatem sed.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Justify,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Lista numerada
                    val numberedItems = listOf(
                        "Ea voluptates omnis aut sequi sequi.",
                        "Est dolore quae in cliquiid ducimus et qutem repellendus.",
                        "Aut ipsum Quis qui porro quasi aut minus placeat!",
                        "Ut sit consequatur neque aut vitae facere."
                    )

                    numberedItems.forEachIndexed { index, item ->
                        Text(
                            text = "${index + 1}. $item",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Justify,
                            lineHeight = 18.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Aut quidem accusantium nam alias autem eum officiis placeat et omnis autem id officiis perspiciatis qui corrupti officia eum aliquam provident. Eum voluptas error et optio dolorum cum molestiae nobis et odit voluptates vel magna impedit sed fugiat nihil vitae.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Justify,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Lista con bullets
                    val bulletItems = listOf(
                        "Aut fuga sequi eum voluptapibus provident.",
                        "Eos consequuntur voluptas sit amet perferendis ut dignissimos velit."
                    )

                    bulletItems.forEach { item ->
                        Text(
                            text = "• $item",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Justify,
                            lineHeight = 18.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Vel exercitationem quam vel eligendu rerum At harum obcaecati et nostrum beatae? Ea accusantium dolorem qui aliquam provident id perferiendis mollitia et ipsum ipsa qui enim autem At corporis sunt. Aut odit quisquam est reprehenderit itaque id accusantium dolor qui neque repellat.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Justify,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Texto con link
                    val annotatedText = buildAnnotatedString {
                        append("Read the terms and conditions in more detail at\n")
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF0099FF),
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("www.finwiseapp.de")
                        }
                    }

                    Text(
                        text = annotatedText,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Checkbox con texto
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                isAccepted = !isAccepted
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isAccepted,
                            onCheckedChange = { isAccepted = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colorScheme.primary,
                                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "I accept all the terms and conditions",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Boton Accept
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = onAccept,
                            enabled = isAccepted,
                            modifier = Modifier
                                .width(189.dp)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                            ),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Text(
                                text = "Accept",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(160.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 932)
@Composable
fun TermsAndConditionsScreenPreview() {
    FinanceAppTheme(darkTheme = false) { // Cambia a 'true' para probar modo oscuro
        TermsAndConditionsScreen(darkTheme = false) // Cambia a 'true' para probar modo oscuro
    }
}