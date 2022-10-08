package com.example.kmmnoteapp.datasource

import com.example.kmmnoteapp.sqldelight.AppDatabase
import com.example.kmmnoteapp.DatabaseDriverFactory
import note.Note

class NoteRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) : NoteRepository {

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val noteQueries = database.noteQueries

    override suspend fun addNote(note: Note) {
        noteQueries.insertOrUpdateNote(
            id = note.id,
            lastEditTime = note.lastEditTime,
            content = note.content
        )
    }

    override suspend fun modifyNote(note: Note) {
        noteQueries.insertOrUpdateNote(
            id = note.id,
            lastEditTime = note.lastEditTime,
            content = note.content
        )
    }

    override suspend fun getAllNote(): List<Note> {
        return noteQueries.getAllNote().executeAsList()
    }

    override suspend fun getNoteById(id: Long): Note? {
        return noteQueries.getNoteById(id).executeAsOneOrNull()
    }

    override suspend fun getNoteByContent(content: String): List<Note> {
        return noteQueries.getNotByContent(content).executeAsList()
    }
}