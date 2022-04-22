package com.example.feature_note_add.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core_ui.enum.ColorNote
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomDrawerView(
    drawerState: BottomDrawerState,
    colorNoteState: (String) -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    BottomDrawer(
        drawerState = drawerState,
        drawerContent = {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(150.dp),
                content = {
                    ColorNote.values().forEach { colorNote ->
                        item {
                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(5.dp)
                                    .clip(AbsoluteRoundedCornerShape(15.dp))
                                    .background(colorNote.color)
                                    .border(
                                        1.dp,
                                        Color.Black,
                                        AbsoluteRoundedCornerShape(15.dp)
                                    )
                                    .clickable {
                                        colorNoteState(colorNote.name)
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                            )
                        }
                    }
            })
        }, content = content
    )
}