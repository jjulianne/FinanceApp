package com.example.financeapp.di

import android.content.Context
import androidx.room.Room
import com.example.financeapp.data.repository.room.AppDatabase
import com.example.financeapp.data.repository.room.UserSessionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context //
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "finance_app_db"
        ).build()
    }

    @Provides
    fun provideUserSessionDao(database: AppDatabase): UserSessionDao {
        return database.userSessionDao()
    }
}
