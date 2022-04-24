package com.example.feature_notes_list.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core_ui.primaryBackground
import com.example.feature_notes_list.NotesListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DialogInfoNotes(
    lifecycleScope: LifecycleCoroutineScope,
    lifecycle: Lifecycle,
    notesListViewModel: NotesListViewModel,
    boolean: MutableState<Boolean>
) {
    if (boolean.value){

        var notesCount by remember { mutableStateOf("") }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                notesListViewModel.noteCount.onEach {
                    notesCount = it.toString()
                }.collect()
            }
        }

        AlertDialog(
            shape = AbsoluteRoundedCornerShape(15.dp),
            backgroundColor = primaryBackground,
            onDismissRequest = { boolean.value = false },
            buttons = {},
            text = {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Notes count:",
                            modifier = Modifier.padding(5.dp)
                        )

                        Text(
                            text = notesCount,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Divider()
                }
            }
        )
    }
}