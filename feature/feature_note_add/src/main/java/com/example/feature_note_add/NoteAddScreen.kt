package com.example.feature_note_add

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.example.core_ui.secondaryText
import com.example.feature_note_add.states.BottomDrawerStates
import com.example.feature_note_add.view.BottomDrawerView
import com.example.feature_note_add.view.dialog.DialogColorThemeNote
import com.example.feature_note_add.view.dialog.DialogSaveChangesNoteAdd


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NoteAddScreen(
    navController: NavController,
    noteAddViewModel: NoteAddViewModel
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var colorSecondaryNote by remember { mutableStateOf(ColorNote.SECONDARY) }
    var colorTextNote by remember { mutableStateOf(ColorNote.WHERE) }
    var colorPrimaryNote by remember { mutableStateOf(ColorNote.PRIMARY) }
    val bottomDrawerStates = remember { mutableStateOf(BottomDrawerStates.SECONDARY) }
    val dialogKeyboardArrowLeftNoteAddCheck = remember { mutableStateOf(false) }
    val dialogColorThemCheck = remember { mutableStateOf(false) }
    val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorPrimaryNote.color
    ) {
        BottomDrawerView(
            drawerState = drawerState,
            colorNoteState = {
                when(bottomDrawerStates.value){
                    BottomDrawerStates.SECONDARY -> {
                        colorSecondaryNote = enumValueOf(it)
                    }
                    BottomDrawerStates.PRIMARY -> {
                        colorPrimaryNote = enumValueOf(it)
                    }
                    BottomDrawerStates.TEXT -> {
                        colorTextNote = enumValueOf(it)

                    }
                }
            }
        ){
            DialogSaveChangesNoteAdd(
                noteAddViewModel = noteAddViewModel,
                navController = navController,
                boolean = dialogKeyboardArrowLeftNoteAddCheck,
                title = title,
                description = description,
                colorSecondary = colorSecondaryNote.name,
                colorPrimary = colorPrimaryNote.name,
                colorText = colorTextNote.name
            )

            DialogColorThemeNote(
                bottomDrawerStates = bottomDrawerStates,
                boolean = dialogColorThemCheck,
                colorPrimaryNote = colorPrimaryNote,
                colorSecondaryNote = colorSecondaryNote,
                colorTextNote = colorTextNote,
                drawerState = drawerState
            )

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
                            .background(colorSecondaryNote.color),
                        onClick = { dialogKeyboardArrowLeftNoteAddCheck.value = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Row {
                        IconButton(
                            modifier = Modifier
                                .padding(
                                    end = 15.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                )
                                .clip(AbsoluteRoundedCornerShape(10.dp))
                                .background(colorSecondaryNote.color),
                            onClick = { dialogColorThemCheck.value = true }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.awesome),
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
                                .background(colorSecondaryNote.color),
                            onClick = {
                                noteAddViewModel.insertNote(
                                    note = Note(
                                        id = 0,
                                        title = title,
                                        description = description,
                                        date = noteAddViewModel.getCurrentTime(),
                                        colorSecondary = colorSecondaryNote.name,
                                        colorPrimary = colorPrimaryNote.name,
                                        colorText = colorTextNote.name
                                    ), navController = navController
                                )
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.save),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
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
                        textColor = colorTextNote.color,
                        backgroundColor = colorPrimaryNote.color,
                        cursorColor = colorTextNote.color,
                        focusedIndicatorColor = colorPrimaryNote.color
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
                        textColor = colorTextNote.color,
                        backgroundColor = colorPrimaryNote.color,
                        cursorColor = colorTextNote.color,
                        focusedIndicatorColor = colorPrimaryNote.color
                    )
                )
            }
        }
    }
}