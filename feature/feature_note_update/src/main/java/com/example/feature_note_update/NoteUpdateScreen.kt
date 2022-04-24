package com.example.feature_note_update

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import com.example.core_utils.navigation.NoteRoute
import com.example.feature_note_add.states.BottomDrawerStates
import com.example.feature_note_add.view.BottomDrawerView
import com.example.feature_note_add.view.dialog.DialogColorThemeNote
import com.example.feature_note_update.view.DialogSaveChangesNoteUpdate
import com.example.feature_note_update.viewModel.NoteUpdateViewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NoteUpdateScreen(
    noteUpdateViewModel: NoteUpdateViewModel,
    navController: NavController,
    noteId:Int
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var colorSecondary by remember { mutableStateOf(ColorNote.SECONDARY.name) }
    var colorText by remember { mutableStateOf(ColorNote.WHERE.name) }
    var colorPrimary by remember { mutableStateOf(ColorNote.PRIMARY.name) }
    val bottomDrawerStates = remember { mutableStateOf(BottomDrawerStates.SECONDARY) }
    val dialogSaveChangesNoteUpdateCheck = remember { mutableStateOf(false) }
    val dialogColorThemCheck = remember { mutableStateOf(false) }
    val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)

    LaunchedEffect(key1 = Unit, block = {
        val note = noteUpdateViewModel.realmNote(noteId)
        title = note.title
        description = note.description
        colorSecondary = note.colorSecondary
        colorText = note.colorText
        colorPrimary = note.colorPrimary
        date = note.date
    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = enumValueOf<ColorNote>(colorPrimary).color
    ) {
        BottomDrawerView(
            drawerState = drawerState,
            colorNoteState = {
                when(bottomDrawerStates.value){
                    BottomDrawerStates.SECONDARY -> {
                        colorSecondary = enumValueOf<ColorNote>(it).name
                    }
                    BottomDrawerStates.PRIMARY -> {
                        colorPrimary = enumValueOf<ColorNote>(it).name
                    }
                    BottomDrawerStates.TEXT -> {
                        colorText = enumValueOf<ColorNote>(it).name
                    }
                }
            }
        ){

            DialogColorThemeNote(
                bottomDrawerStates = bottomDrawerStates,
                boolean = dialogColorThemCheck,
                colorPrimaryNote = enumValueOf(colorPrimary),
                colorSecondaryNote = enumValueOf(colorSecondary),
                colorTextNote = enumValueOf(colorText),
                drawerState = drawerState
            )

            DialogSaveChangesNoteUpdate(
                noteUpdateViewModel = noteUpdateViewModel,
                boolean = dialogSaveChangesNoteUpdateCheck,
                navController = navController,
                noteId = noteId,
                title = title,
                description = description,
                date = date,
                colorSecondary = colorSecondary,
                colorPrimary = colorPrimary,
                colorText = colorText
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
                            .background(enumValueOf<ColorNote>(colorSecondary).color),
                        onClick = { dialogSaveChangesNoteUpdateCheck.value = true }
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
                                .background(enumValueOf<ColorNote>(colorSecondary).color),
                            onClick = { dialogColorThemCheck.value = true }
                        ) {
                            Icon(
                                painter = painterResource(id = com.example.feature_note_add.R.drawable.awesome),
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
                                .background(enumValueOf<ColorNote>(colorSecondary).color),
                            onClick = {
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
                            Icon(
                                painter = painterResource(id = com.example.feature_note_add.R.drawable.save),
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
                        textColor = enumValueOf<ColorNote>(colorText).color,
                        backgroundColor = enumValueOf<ColorNote>(colorPrimary).color,
                        cursorColor = enumValueOf<ColorNote>(colorText).color,
                        focusedIndicatorColor = enumValueOf<ColorNote>(colorPrimary).color
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
                        textColor = enumValueOf<ColorNote>(colorText).color,
                        backgroundColor = enumValueOf<ColorNote>(colorPrimary).color,
                        cursorColor = enumValueOf<ColorNote>(colorText).color,
                        focusedIndicatorColor = enumValueOf<ColorNote>(colorPrimary).color
                    )
                )
            }
        }
    }
}