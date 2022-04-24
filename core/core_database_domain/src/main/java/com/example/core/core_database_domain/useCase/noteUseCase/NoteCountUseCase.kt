package com.example.core.core_database_domain.useCase.noteUseCase

import com.example.core.core_database_domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteCountUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke():Flow<Int>{
        return noteRepository.noteCount()
    }
}