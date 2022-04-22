package com.example.feature_notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.useCase.noteUseCase.InsertNoteUseCase
import com.example.core.core_database_domain.useCase.noteUseCase.RealmAddNoteUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NotesListViewModel @Inject constructor(
    private val realmAddNoteUseCase: RealmAddNoteUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
):ViewModel() {

    private val _responseNotes:MutableStateFlow<Response<List<Note>>> = MutableStateFlow(Response.Loading())
    val responseNotes: StateFlow<Response<List<Note>>> = _responseNotes.asStateFlow()

    init {
        insertNote()
    }

    fun realmAddNote(){
        realmAddNoteUseCase().onEach {
            _responseNotes.value = it
        }.launchIn(viewModelScope)
    }

    private fun insertNote(){
        repeat(10){
            insertNoteUseCase.invoke(
                Note(
                    id = 0,
                    title = "test_$it",
                    description = "test",
                    date = "test"
                )
            ).launchIn(viewModelScope)
        }
    }
}