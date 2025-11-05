package com.example.financeapp.ui.screens.help

/**
 * Modelo de datos para una sola pregunta frecuente (FAQ).
 * Incluimos 'category' para poder filtrar.
 */
data class FaqItem(
    val id: Int,
    val question: String,
    val answer: String,
    val category: String // "General", "Account", "Services"
)

/**
 * Define el estado de la UI para la pantalla de Ayuda.
 */
data class HelpUiState(
    val selectedTab: String = "FAQ",
    val selectedCategory: String = "General",
    val searchQuery: String = "",

    val isLoading: Boolean = true,
    val allFaqs: List<FaqItem> = emptyList(),      // La lista completa de la API/DB
    val filteredFaqs: List<FaqItem> = emptyList(), // La lista que ve el usuario
    val errorMessage: String? = null
)