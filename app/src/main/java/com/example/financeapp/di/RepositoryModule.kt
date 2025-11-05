package com.example.financeapp.di

import com.example.financeapp.data.repository.AuthRepositoryImpl
import com.example.financeapp.data.repository.ChatRepositoryImpl
import com.example.financeapp.data.repository.FingerprintRepositoryImpl
import com.example.financeapp.data.repository.SettingsRepositoryImpl
import com.example.financeapp.data.repository.UserRepositoryImpl
import com.example.financeapp.domain.repository.AuthRepository
import com.example.financeapp.domain.repository.ChatRepository
import com.example.financeapp.domain.repository.FingerprintRepository
import com.example.financeapp.domain.repository.SettingsRepository
import com.example.financeapp.domain.repository.UserRepository

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

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    @Singleton
    abstract fun bindChatRepository(
        impl: ChatRepositoryImpl
    ): ChatRepository

    @Binds
    @Singleton
    abstract fun bindFingerprintRepository(
        impl: FingerprintRepositoryImpl
    ): FingerprintRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    // Aca van los binds para TransactionRepository,
    // ProfileRepository, SavingRepository, etc., a medida que los implemente.
}