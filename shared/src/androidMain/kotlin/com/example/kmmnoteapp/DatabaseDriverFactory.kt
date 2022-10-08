package com.example.kmmnoteapp

import android.content.Context
import com.example.kmmnoteapp.sqldelight.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver


actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {

        return AndroidSqliteDriver(AppDatabase.Schema, context, Constant.NOTE_DB)
    }
}

class DatabaseDriver(private val context: Context): DatabaseDriverInterface {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "Image.db")
    }
}