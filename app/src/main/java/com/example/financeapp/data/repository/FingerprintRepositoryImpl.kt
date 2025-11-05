package com.example.financeapp.data.repository

import com.example.financeapp.domain.repository.FingerprintRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FingerprintRepositoryImpl @Inject constructor(
    // private val dao: FingerprintDao // <-- Cuando integremos Room, Hilt lo inyecta aca
) : FingerprintRepository {

    override suspend fun deleteFingerprint(name: String) {
        // Aca tenemos que implentar la logica real para borrar de Room:
        // dao.deleteByName(name)

        // Por ahora, para pruebas estoy simulando el delay
        delay(2000L)
        println("Huella '$name' borrada (pruebas)")
    }
}