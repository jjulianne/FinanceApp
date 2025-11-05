package com.example.financeapp.domain.model

/* Representa una transacción individual */
data class TransactionItem(
    val id: String,
    val iconId: Int,
    val title: String,     // Descripción de la transacción
    val timeDate: String,  // Fecha y hora
    val category: String,  // Subtipo
    val amount: String,    // Monto
    val isExpense: Boolean, // True si es un gasto, False si es un ingreso
    val iconBackgroundColorId: Int // Color de fondo del ícono
)