package com.example.core.core_database_domain.useCase.noteUseCase

import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(note: Note):Flow<Response<Void?>> = flow{
        try {
            emit(Response.Loading<Void?>())
            noteRepository.insertNote(note)
            emit(Response.Success<Void?>(null))
        }catch (e:Exception){
            emit(Response.Error<Void?>(message = e.message.toString()))
        }
    }
}