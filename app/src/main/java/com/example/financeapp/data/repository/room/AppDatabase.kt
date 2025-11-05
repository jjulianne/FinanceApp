package com.example.financeapp.data.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserSessionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userSessionDao(): UserSessionDao
}
