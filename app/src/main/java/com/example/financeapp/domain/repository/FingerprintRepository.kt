package com.example.financeapp.domain.repository

interface FingerprintRepository {
    suspend fun deleteFingerprint(name: String)
}