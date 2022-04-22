package com.example.feature_note_add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core.core_database_domain.entity.Note
import com.example.core_ui.enum.ColorNote
import com.example.core_ui.primaryBackground
import com.example.core_ui.secondaryBackground
import com.example.core_ui.secondaryText
import com.example.core_utils.navigation.NoteRoute
import com.example.feature_note_add.states.BottomDrawerStates
import com.example.feature_note_add.view.BottomDrawerView

@ExperimentalMaterialApi
@Composable
fun NoteAddScreen(
    navController: NavController,
    noteAddViewModel: NoteAddViewModel
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var colorNote by remember { mutableStateOf(ColorNote.RED) }
    val bottomDrawerStates = remember { mutableStateOf(BottomDrawerStates.SECONDARY) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = primaryBackground
    ) {
        BottomDrawerView(
            colorNoteState = {
                colorNote = enumValueOf(it)
            },
            bottomDrawerStates = bottomDrawerStates
        ){
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
                            .background(secondaryBackground),
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
                            .background(secondaryBackground),
                        onClick = {
                            if (title.isNotEmpty() && description.isNotEmpty()){
                                noteAddViewModel.insertNote(
                                    note = Note(
                                        id = 0,
                                        title = title,
                                        description = description,
                                        date = noteAddViewModel.getCurrentTime(),
                                        color = colorNote.name
                                    ), navController = navController
                                )
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.save),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text(text = "Title", color = secondaryText) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = primaryBackground,
                        cursorColor = Color.White,
                        focusedIndicatorColor = primaryBackground
                    )
                )

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text(text = "Type something...", color = secondaryText) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = primaryBackground,
                        cursorColor = Color.White,
                        focusedIndicatorColor = primaryBackground
                    )
                )
            }
        }
    }
}