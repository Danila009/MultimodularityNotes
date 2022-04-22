package com.example.feature_notes_list

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.example.core.core_database_domain.common.Response
import com.example.core.core_database_domain.entity.Note
import com.example.core_ui.enum.ColorNote
import com.example.core_ui.primaryBackground
import com.example.core_ui.secondaryBackground
import com.example.core_utils.navigation.NoteRoute
import com.example.feature_notes_list.states.SearchStates
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NotesListScreen(
    lifecycleScope: LifecycleCoroutineScope = LocalLifecycleOwner.current.lifecycleScope,
    lifecycle:Lifecycle = LocalLifecycleOwner.current.lifecycle,
    notesListViewModel: NotesListViewModel,
    navController: NavController
) {
    var searchStates by remember { mutableStateOf(SearchStates.CLOSE) }
    var search by remember { mutableStateOf("") }
    var notes:Response<List<Note>> by remember { mutableStateOf(Response.Loading()) }

    notesListViewModel.realmAddNote(search = search)

    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
            notesListViewModel.responseNotes.collect{ resource ->
                notes = resource
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(
                visible = searchStates == SearchStates.CLOSE,
                enter = expandHorizontally(),
                exit = shrinkHorizontally()
            ) {
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(15.dp,15.dp),
                    backgroundColor = primaryBackground,
                    onClick = { navController.navigate(NoteRoute.NoteAddScreen.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                Column {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )
                    AnimatedVisibility(
                        visible = searchStates == SearchStates.CLOSE,
                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Notes",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                modifier = Modifier.padding(
                                    top = 5.dp,
                                    start = 20.dp,
                                    bottom = 5.dp
                                )
                            )

                            Row {
                                IconButton(
                                    modifier = Modifier
                                        .padding(
                                            5.dp
                                        )
                                        .clip(AbsoluteRoundedCornerShape(10.dp))
                                        .background(secondaryBackground),
                                    onClick = { searchStates = SearchStates.OPEN }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                                IconButton(
                                    modifier = Modifier
                                        .padding(
                                            top = 5.dp,
                                            end = 20.dp,
                                            bottom = 5.dp
                                        )
                                        .clip(AbsoluteRoundedCornerShape(10.dp))
                                        .background(secondaryBackground),
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }

                    AnimatedVisibility(
                        visible = searchStates == SearchStates.OPEN,
                        enter = slideInHorizontally(),
                        exit = slideOutHorizontally()
                    ){
                        TextField(
                            value = search,
                            onValueChange = { search = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .clip(AbsoluteRoundedCornerShape(15.dp)),
                            placeholder = {
                                Text(
                                    text = "Search by the keyword...",
                                    color = Color.White
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    search = ""
                                    searchStates = SearchStates.CLOSE
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White,
                                backgroundColor = secondaryBackground,
                                cursorColor = Color.White,
                                focusedIndicatorColor = secondaryBackground
                            )
                        )
                    }

                    notes.data?.let {
                        if (it.isNotEmpty()){
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    items(
                                        items = notes.data ?: emptyList()
                                    ) { item ->
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(
                                                    vertical = 5.dp,
                                                    horizontal = 10.dp
                                                )
                                                .clickable {
                                                    navController.navigate(NoteRoute.NoteInfoScreen.data(
                                                        noteId = item.id
                                                    ))
                                                },
                                            shape = AbsoluteRoundedCornerShape(15.dp),
                                            backgroundColor = enumValueOf<ColorNote>(item.colorSecondary).color
                                        ) {
                                            Column {
                                                Text(
                                                    text = item.title,
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                        .fillMaxWidth(),
                                                    textAlign = TextAlign.Center,
                                                    color = enumValueOf<ColorNote>(item.colorText).color
                                                )

                                                Text(
                                                    text = item.date,
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                        .fillMaxWidth(),
                                                    textAlign = TextAlign.End,
                                                    color = enumValueOf<ColorNote>(item.colorText).color
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                        }else{
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                )
                                Image(
                                    painter = painterResource(id =
                                        if(searchStates == SearchStates.CLOSE) R.drawable.rafiki
                                        else R.drawable.cuate
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(300.dp)
                                )
                                Text(
                                    text = if(searchStates == SearchStates.CLOSE) "Create your first note !"
                                        else "File not found. Try searching again.",
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}