package com.example.feature_note_update.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.useCase.noteUseCase.RealmNoteUseCase
import com.example.core.core_database_domain.useCase.noteUseCase.UpdateNoteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteUpdateViewModel @Inject constructor(
    private val realmNoteUseCase: RealmNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
):ViewModel() {

    suspend fun realmNote(idNote: Int):Note{
        return realmNoteUseCase.invoke(idNote)
    }

    fun updateNote(note:Note){
        viewModelScope.launch {
            updateNoteUseCase.invoke(note)
        }
    }
}