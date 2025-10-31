package com.example.financeapp.di

import com.example.financeapp.data.repository.AuthRepositoryImpl
import com.example.financeapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Define la vinculacion para el AuthRepository.
     * Hilt sabe que cuando se solicite AuthRepository (la Interfaz),
     * debe proporcionar una instancia de AuthRepositoryImpl (la Implementacion).
     */
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    // Aca van los binds para TransactionRepository,
    // ProfileRepository, SavingRepository, etc., a medida que los implemente.
}