package com.example.core.core_database_domain.useCase.noteUseCase

import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RealmAddNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke():Flow<Response<List<Note>>> = flow{
        try {
            emit(Response.Loading<List<Note>>())
            val response = noteRepository.realmAddNote()
            emit(Response.Success(data = response))
        }catch (e:Exception){
            emit(Response.Error<List<Note>>(message = e.message.toString()))
        }
    }
}