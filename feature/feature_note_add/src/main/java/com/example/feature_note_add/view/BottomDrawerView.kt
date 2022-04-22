package com.example.feature_note_add.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core_ui.enum.ColorNote
import com.example.core_ui.primaryBackground
import com.example.core_ui.secondaryBackground
import com.example.feature_note_add.states.BottomDrawerStates
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomDrawerView(
    bottomDrawerStates: MutableState<BottomDrawerStates>,
    colorNoteState: (String) -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val backdropState = rememberBackdropScaffoldState(
        initialValue =  if (bottomDrawerStates.value != BottomDrawerStates.CLOSE)
            BackdropValue.Concealed else BackdropValue.Revealed
            )

    BackdropScaffold(
        frontLayerBackgroundColor = secondaryBackground,
        backLayerBackgroundColor = primaryBackground,
        scaffoldState = backdropState,
        headerHeight = 0.dp,
        appBar = {},
        backLayerContent = content,
        frontLayerContent = {
            when(bottomDrawerStates.value){
                BottomDrawerStates.SECONDARY -> {
                    LazyRow(content = {
                        item {
                            ColorNote.values().forEach { colorNote ->
                                Column(
                                    modifier = Modifier
                                        .width(100.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Card(
                                        backgroundColor = colorNote.color,
                                        shape = AbsoluteRoundedCornerShape(15.dp),
                                        modifier = Modifier
                                            .size(200.dp)
                                            .padding(5.dp)
                                            .border(
                                                1.dp,
                                                Color.Black,
                                                AbsoluteRoundedCornerShape(15.dp)
                                            )
                                            .clickable {
                                                colorNoteState(colorNote.name)
                                                bottomDrawerStates.value = BottomDrawerStates.CLOSE
                                                scope.launch {
                                                    backdropState.reveal()
                                                }
                                            }
                                    ){}
                                    Text(
                                        text = colorNote.name,
                                        modifier = Modifier
                                            .padding(5.dp),
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    })
                }
                BottomDrawerStates.PRIMARY -> {

                }
                else -> Unit
            }
        }
    )
}