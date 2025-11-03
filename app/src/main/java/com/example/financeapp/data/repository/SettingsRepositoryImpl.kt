package com.example.financeapp.data.repository

import com.example.financeapp.data.datastore.SettingsDataStore
import com.example.financeapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override fun getThemeSetting(): Flow<Boolean> {
        return settingsDataStore.getThemeSetting
    }

    override suspend fun saveThemeSetting(isDark: Boolean) {
        settingsDataStore.saveThemeSetting(isDark)
    }
}