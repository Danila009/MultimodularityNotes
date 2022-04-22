package com.example.feature_notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.useCase.noteUseCase.RealmAddNoteUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NotesListViewModel @Inject constructor(
    private val realmAddNoteUseCase: RealmAddNoteUseCase,
):ViewModel() {

    private val _responseNotes:MutableStateFlow<Response<List<Note>>> = MutableStateFlow(Response.Loading())
    val responseNotes: StateFlow<Response<List<Note>>> = _responseNotes.asStateFlow()

    fun realmAddNote(search:String){
        realmAddNoteUseCase(search).onEach {
            _responseNotes.value = it
        }.launchIn(viewModelScope)
    }
}