package com.example.core.core_database_data.repositoryImpl

import android.util.Log
import com.example.core.core_database_data.database.NoteDao
import com.example.core.core_database_data.entities.NoteEntities
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
):NoteRepository {
    override suspend fun insertNote(note: Note) {
        val noteEntities = NoteEntities(
            id = note.id,
            title = note.title,
            description = note.description,
            date = note.date,
            colorPrimary = note.colorPrimary,
            colorSecondary = note.colorSecondary,
            colorText = note.colorText
        )
        noteDao.insertNote(noteEntities)
    }

    override suspend fun deleteNote(id: Int) {
        noteDao.realmNote(id).onEach { note ->
            val noteEntities = NoteEntities(
                id = note.id,
                title = note.title,
                description = note.description,
                date = note.date,
                colorPrimary = note.colorPrimary,
                colorSecondary = note.colorSecondary,
                colorText = note.colorText
            )
            noteDao.deleteNote(noteEntities)
        }.collect()
    }

    override suspend fun updateNote(note: Note) {
        val noteEntities = NoteEntities(
            id = note.id,
            title = note.title,
            description = note.description,
            date = note.date,
            colorPrimary = note.colorPrimary,
            colorSecondary = note.colorSecondary,
            colorText = note.colorText
        )
        noteDao.updateNote(noteEntities)
    }

    override suspend fun realmAddNote(search:String): Flow<List<Note>> {
        val response = noteDao.realmAddNote(search)
        return response.map { value: List<NoteEntities> ->
            value.map { noteEntity ->
                Note(
                    id = noteEntity.id,
                    date = noteEntity.date,
                    description = noteEntity.description,
                    title = noteEntity.title,
                    colorPrimary = noteEntity.colorPrimary,
                    colorSecondary = noteEntity.colorSecondary,
                    colorText = noteEntity.colorText
                )
            }
        }
    }

    override suspend fun realmNote(id: Int): Flow<Note> {
        return noteDao.realmNote(id)
            .map { response ->
                Note(
                    id = response.id,
                    title = response.title,
                    description = response.description,
                    date = response.date,
                    colorPrimary = response.colorPrimary,
                    colorSecondary = response.colorSecondary,
                    colorText = response.colorText
                )
            }
    }
}