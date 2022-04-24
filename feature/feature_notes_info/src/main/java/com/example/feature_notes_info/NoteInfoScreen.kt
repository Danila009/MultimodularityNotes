package com.example.feature_notes_info

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.example.core.core_database_domain.entity.Note
import com.example.core_ui.enum.ColorNote
import com.example.core_utils.navigation.NoteRoute
import com.example.feature_notes_info.viewModel.NoteInfoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NoteInfoScreen(
    lifecycleScope: LifecycleCoroutineScope = LocalLifecycleOwner.current.lifecycleScope,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    noteInfoViewModel: NoteInfoViewModel,
    navController: NavController,
    idNote:Int
) {
    var note by remember { mutableStateOf(Note()) }

    noteInfoViewModel.realmNote(idNote)

    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
            noteInfoViewModel.responseNote.collect{ resource ->
                resource.data?.let {
                    note = it
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = enumValueOf<ColorNote>(note.colorPrimary).color
    ) {
        LazyColumn {
            item {
                Column {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(
                                    start = 15.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                )
                                .clip(AbsoluteRoundedCornerShape(10.dp))
                                .background(enumValueOf<ColorNote>(note.colorSecondary).color),
                            onClick = { navController.navigate(NoteRoute.NoteListScreen.route) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                        IconButton(
                            modifier = Modifier
                                .padding(
                                    end = 15.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                )
                                .clip(AbsoluteRoundedCornerShape(10.dp))
                                .background(enumValueOf<ColorNote>(note.colorSecondary).color),
                            onClick = { navController.navigate(NoteRoute.NoteUpdateScreen.data(
                                noteId = note.id
                            )) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                    Text(
                        text = note.title,
                        color = enumValueOf<ColorNote>(note.colorText).color,
                        modifier = Modifier
                            .padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Text(
                        text = note.description,
                        color = enumValueOf<ColorNote>(note.colorText).color,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}