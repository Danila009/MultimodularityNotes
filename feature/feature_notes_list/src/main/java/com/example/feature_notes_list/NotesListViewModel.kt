package com.example.feature_notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.useCase.noteUseCase.DeleteNoteUseCase
import com.example.core.core_database_domain.useCase.noteUseCase.NoteCountUseCase
import com.example.core.core_database_domain.useCase.noteUseCase.RealmAddNoteUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesListViewModel @Inject constructor(
    private val realmAddNoteUseCase: RealmAddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    noteCountUseCase: NoteCountUseCase
):ViewModel() {

    private val _responseNotes:MutableStateFlow<Response<List<Note>>> = MutableStateFlow(Response.Loading())
    val responseNotes: StateFlow<Response<List<Note>>> = _responseNotes.asStateFlow()

    val noteCount = noteCountUseCase.invoke()

    fun realmAddNote(search:String){
        realmAddNoteUseCase(search).onEach {
            _responseNotes.value = it
        }.launchIn(viewModelScope)
    }

    fun deleteNote(id:Int){
        viewModelScope.launch {
            deleteNoteUseCase.invoke(id)
        }
    }
}