package com.example.financeapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

// Creamos una "instancia" de DataStore a nivel de la app.
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

// Aca le ponemos un nombre a la preferencia que queremos guardar
private object PreferencesKeys {
    val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
}

/**
 * Esta clase "gestiona" la lectura y escritura en el DataStore.
 * Hilt (@Inject) se encargará de construirla y entregarla
 * al SettingsRepositoryImpl.
 */
@Singleton
class SettingsDataStore @Inject constructor(@ApplicationContext context: Context) {

    // Obtenemos la instancia que creamos arriba
    private val settingsDataStore = context.dataStore

    // Exponemos un "Flow" que pasa
    // el valor (true/false) cada vez que cambie
    val getThemeSetting: Flow<Boolean> = settingsDataStore.data
        .catch { exception ->
            // Si hay un error (ej. al leer el archivo), emitimos un Flow vacoo
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            // Leemos el valor de nuestro cajón "IS_DARK_MODE"
            // Si no existe (ej. la primera vez que se abre la app),
            // devolvemos 'false' (modo claro) por defecto.
            preferences[PreferencesKeys.IS_DARK_MODE] ?: false
        }

    // Esta es una funcion "suspend" porque escribir en el disco
    // es una operación asíncrona
    suspend fun saveThemeSetting(isDark: Boolean) {
        settingsDataStore.edit { preferences ->
            // Guardamos el nuevo valor (true o false)
            preferences[PreferencesKeys.IS_DARK_MODE] = isDark
        }
    }
}