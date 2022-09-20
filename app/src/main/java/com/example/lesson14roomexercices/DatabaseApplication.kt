package com.example.lesson14roomexercices

import android.app.Application
import android.content.Context
import androidx.room.Room

class DatabaseApplication : Application() {
    var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "database"
        ).allowMainThreadQueries().build()
    }
}

val Context.appDatabase: AppDatabase
    get() = when (this) {
        is DatabaseApplication -> requireNotNull(database)
        else -> applicationContext.appDatabase
    }