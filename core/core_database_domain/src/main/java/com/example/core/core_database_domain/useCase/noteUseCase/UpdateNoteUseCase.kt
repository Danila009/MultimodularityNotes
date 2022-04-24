package com.example.core.core_database_domain.useCase.noteUseCase

import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note:Note){
        noteRepository.updateNote(note)
    }
}