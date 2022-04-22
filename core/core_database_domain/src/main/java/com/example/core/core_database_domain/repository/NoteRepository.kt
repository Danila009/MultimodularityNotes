package com.example.core.core_database_domain.repository

import com.example.core.core_database_domain.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)

    suspend fun deleteNote(id: Int)

    suspend fun updateNote(note: Note)

    suspend fun realmAddNote(search:String):Flow<List<Note>>

    suspend fun realmNote(id:Int):Flow<Note>
}