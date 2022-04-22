package com.example.feature_notes_list

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NotesListScreen(
    lifecycleScope: LifecycleCoroutineScope = LocalLifecycleOwner.current.lifecycleScope,
    lifecycle:Lifecycle = LocalLifecycleOwner.current.lifecycle,
    notesListViewModel: NotesListViewModel
) {
    var notes:Response<List<Note>> by remember { mutableStateOf(Response.Loading()) }

    notesListViewModel.realmAddNote()

    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
            notesListViewModel.responseNotes.collect{ resource ->
                notes = resource
            }
        }
    }

    when(notes){
        is Response.Error -> {
            Text(text = notes.message.toString())
        }
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            LazyColumn(content = {
                items(notes.data!!){ item ->
                    Text(text = item.title)
                }
            })
        }
    }
}