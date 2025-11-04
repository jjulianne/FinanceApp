package com.example.financeapp.domain.model


data class User(
    val user_id: String = "",
    val name: String = "",
    val email: String = "",
    val balance: Double = 0.0
)

data class CreditCard(
    val card_number: String,
    val cardholder_name: String,
    val expiration_date: String,
    val cvv: String,
    val credit_limit: Double,
    val current_balance: Double,
    val available_balance: Double
)

data class BankAccount(
    val bank_name: String,
    val account_type: String,
    val cvu: String,
    val alias: String,
    val currency: String
)
