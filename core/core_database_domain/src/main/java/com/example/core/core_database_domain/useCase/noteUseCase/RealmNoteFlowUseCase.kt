package com.example.core.core_database_domain.useCase.noteUseCase

import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RealmNoteFlowUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(idNote:Int):Flow<Response<Note>> = flow{
        try {
            noteRepository.realmFlowNote(idNote).onEach {
                emit(Response.Success(data = it))
            }.collect()
        }catch (e:Exception){
            emit(Response.Error<Note>(message = e.message.toString()))
        }
    }
}