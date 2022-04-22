package com.example.core.core_database_data.repositoryImpl

import com.example.core.core_database_data.database.NoteDao
import com.example.core.core_database_data.entities.NoteEntities
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.repository.NoteRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
):NoteRepository {
    override suspend fun insertNote(note: Note) {
        val noteEntities = NoteEntities(
            id = note.id,
            title = note.title,
            description = note.description,
            date = note.date
        )
        noteDao.insertNote(noteEntities)
    }

    override suspend fun deleteNote(id: Int) {
        noteDao.realmNote(id).onEach { note ->
            val noteEntities = NoteEntities(
                id = note.id,
                title = note.title,
                description = note.description,
                date = note.date
            )
            noteDao.deleteNote(noteEntities)
        }.collect()
    }

    override suspend fun updateNote(note: Note) {
        val noteEntities = NoteEntities(
            id = note.id,
            title = note.title,
            description = note.description,
            date = note.date
        )
        noteDao.updateNote(noteEntities)
    }

    override suspend fun realmAddNote(): List<Note> {
        val notes = ArrayList<Note>()
        val response = noteDao.realmAddNote()
        response.onEach { result ->
            result.forEach {  noteEntities ->
                notes.add(
                    Note(
                        id = noteEntities.id,
                        title = noteEntities.title,
                        description = noteEntities.description,
                        date = noteEntities.date
                    )
                )
            }
        }.collect()
        return notes
    }

    override suspend fun realmNote(id: Int): Note {
        var note = Note()
        noteDao.realmNote(id).onEach { response ->
            note = Note(
                id = response.id,
                title = response.title,
                description = response.description,
                date = response.date
            )
        }.collect()
        return note
    }
}