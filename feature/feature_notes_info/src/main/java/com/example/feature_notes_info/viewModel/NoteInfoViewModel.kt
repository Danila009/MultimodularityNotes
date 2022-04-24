package com.example.feature_notes_info.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.useCase.noteUseCase.RealmNoteFlowUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NoteInfoViewModel @Inject constructor(
    private val realmNoteUseCase: RealmNoteFlowUseCase
):ViewModel() {

    private val _responseNote:MutableStateFlow<Response<Note>> = MutableStateFlow(Response.Loading())
    val responseNote = _responseNote.asStateFlow()

    fun realmNote(idNote:Int){
        realmNoteUseCase.invoke(idNote).onEach {
            _responseNote.value = it
        }.launchIn(viewModelScope)
    }
}