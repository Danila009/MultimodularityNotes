package com.example.feature_note_update.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core.core_database_domain.entity.Note
import com.example.core_ui.primaryBackground
import com.example.core_utils.navigation.NoteRoute
import com.example.feature_note_update.viewModel.NoteUpdateViewModel

@Composable
fun DialogSaveChangesNoteUpdate(
    noteUpdateViewModel: NoteUpdateViewModel,
    boolean: MutableState<Boolean>,
    navController: NavController,
    noteId:Int,
    title:String,
    description:String,
    date:String,
    colorSecondary:String,
    colorPrimary:String,
    colorText:String
) {
    if (boolean.value){
        AlertDialog(
            backgroundColor = primaryBackground,
            shape = AbsoluteRoundedCornerShape(15.dp),
            onDismissRequest = { boolean.value = false },
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(60.dp)
                    )
                }
            },
            text = {
                Text(
                    text = "Are your sure you want discard your changes ?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier.padding(
                            top = 5.dp,
                            bottom = 20.dp,
                            start = 5.dp,
                            end = 5.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(255,0,0)
                        ),
                        shape = AbsoluteRoundedCornerShape(5.dp),
                        onClick = {
                            boolean.value = false
                            navController.navigate(NoteRoute.NoteInfoScreen.data(noteId)){
                                popUpTo(NoteRoute.NoteInfoScreen.data(noteId)){
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Text(
                            text = "Discard",
                            color = Color.White
                        )
                    }
                    Button(
                        modifier = Modifier.padding(
                            top = 5.dp,
                            bottom = 20.dp,
                            start = 5.dp,
                            end = 5.dp
                        ),
                        shape = AbsoluteRoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(48,190,113)
                        ),
                        onClick = {
                            boolean.value = false
                            noteUpdateViewModel.updateNote(
                                note = Note(
                                    id = noteId,
                                    title = title,
                                    description = description,
                                    date = date,
                                    colorSecondary = colorSecondary,
                                    colorPrimary = colorPrimary,
                                    colorText = colorText
                                )
                            )
                            navController.navigate(NoteRoute.NoteInfoScreen.data(noteId)){
                                popUpTo(NoteRoute.NoteInfoScreen.data(noteId)){
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Text(
                            text = "Save",
                            color = Color.White
                        )
                    }
                }
            }
        )
    }
}