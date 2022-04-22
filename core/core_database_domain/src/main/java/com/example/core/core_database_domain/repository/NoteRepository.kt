package com.example.core.core_database_domain.repository

import com.example.core.core_database_domain.entity.Note

interface NoteRepository {
    suspend fun insertNote(note: Note)

    suspend fun deleteNote(id: Int)

    suspend fun updateNote(note: Note)

    suspend fun realmAddNote():List<Note>

    suspend fun realmNote(id:Int):Note
}