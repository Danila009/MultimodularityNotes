package com.example.feature_note_add.view.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core_ui.enum.ColorNote
import com.example.core_ui.primaryBackground
import com.example.core_ui.secondaryBackground
import com.example.feature_note_add.states.BottomDrawerStates
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun DialogColorThemeNote(
    drawerState:BottomDrawerState,
    boolean: MutableState<Boolean>,
    bottomDrawerStates: MutableState<BottomDrawerStates>,
    colorSecondaryNote: ColorNote,
    colorPrimaryNote: ColorNote,
    colorTextNote: ColorNote,
) {
    val scope = rememberCoroutineScope()

    if (boolean.value){
        AlertDialog(
            backgroundColor = primaryBackground,
            shape = AbsoluteRoundedCornerShape(15.dp),
            onDismissRequest = { boolean.value = false },
            text = {
                Column {
                    Box(modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(AbsoluteRoundedCornerShape(10.dp))
                        .background(color = colorPrimaryNote.color)
                        .border(
                            1.dp,
                            Color.Black,
                            AbsoluteRoundedCornerShape(10.dp)
                        )
                        .clickable {
                            bottomDrawerStates.value = BottomDrawerStates.PRIMARY
                            boolean.value = false
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ){
                        Text(
                            text = "Primary color note",
                            modifier = Modifier
                                .padding(5.dp)
                                .align(Alignment.Center),
                            color = Color.White
                        )
                    }

                    Box(modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(AbsoluteRoundedCornerShape(10.dp))
                        .background(color = colorSecondaryNote.color)
                        .border(
                            1.dp,
                            Color.Black,
                            AbsoluteRoundedCornerShape(10.dp)
                        )
                        .clickable {
                            bottomDrawerStates.value = BottomDrawerStates.SECONDARY
                            boolean.value = false
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ){
                        Text(
                            text = "Secondary color note",
                            modifier = Modifier
                                .padding(5.dp)
                                .align(Alignment.Center),
                            color = Color.White
                        )
                    }

                    TextButton(
                        onClick = {
                            bottomDrawerStates.value = BottomDrawerStates.TEXT
                            boolean.value = false
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Text(
                            text = "Text color note",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            color = colorTextNote.color,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.padding(
                            top = 5.dp,
                            bottom = 20.dp,
                            start = 5.dp,
                            end = 5.dp
                        ),
                        shape = AbsoluteRoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = secondaryBackground
                        ),
                        onClick = { boolean.value = false }
                    ) {
                        Text(
                            text = "Close",
                            color = Color.White
                        )
                    }
                }
            }
        )
    }
}