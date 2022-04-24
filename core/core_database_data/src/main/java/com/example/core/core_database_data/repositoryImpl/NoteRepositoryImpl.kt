package com.example.core.core_database_data.repositoryImpl

import com.example.core.core_database_data.database.NoteDao
import com.example.core.core_database_data.entities.NoteEntities
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
        noteDao.deleteNote(id)
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

    override suspend fun realmFlowNote(id: Int): Flow<Note> {
        return noteDao.realmNoteFlow(id)
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

    override suspend fun realmNote(id: Int): Note {
        val response = noteDao.realmNote(id)
        return Note(
            id = response.id,
            title = response.title,
            description = response.description,
            colorText = response.colorText,
            colorPrimary = response.colorPrimary,
            colorSecondary = response.colorSecondary,
            date = response.date
        )
    }

    override fun noteCount(): Flow<Int> {
        return noteDao.noteCount()
    }
}