package com.example.kmmnoteapp.datasource

import note.Note

interface NoteRepository {
    suspend fun addNote(note: Note)

    suspend fun modifyNote(note: Note)

    suspend fun getAllNote() : List<Note>

    suspend fun getNoteById(id: Long) : Note?

    suspend fun getNoteByContent(content: String) : List<Note>


}