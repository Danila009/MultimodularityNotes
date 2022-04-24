package com.example.feature_note_add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core.core_database_domain.useCase.noteUseCase.InsertNoteUseCase
import com.example.core_utils.navigation.NoteRoute
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NoteAddViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase
):ViewModel() {

    fun insertNote(note: Note, navController: NavController){
        viewModelScope.launch {
            insertNoteUseCase(note)
                .onEach {
                    if (it is Response.Success){
                        navController.navigate(NoteRoute.NoteListScreen.route){
                            popUpTo(NoteRoute.NoteListScreen.route){
                                inclusive = true
                            }
                        }
                    }
                }.collect()
        }
    }

    fun getCurrentTime():String{
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-yy hh:mm", Locale.getDefault())
        return formatter.format(time)
    }
}