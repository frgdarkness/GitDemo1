package com.example.kmmnoteapp

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

interface DatabaseDriverInterface {
    fun createDriver(): SqlDriver
}