package com.example.notes.navigation.navHosts

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core_utils.navigation.NoteConstants.NOTES_ROUTE
import com.example.core_utils.navigation.NoteRoute
import com.example.feature_note_add.NoteAddScreen
import com.example.feature_notes_list.NotesListScreen
import com.example.notes.di.AppComponent

@ExperimentalAnimationApi
fun NavGraphBuilder.notesListNavGraph(
    navController: NavController,
    appComponent: AppComponent
){
    navigation(
        startDestination = NoteRoute.NoteListScreen.route,
        route = NOTES_ROUTE,
        builder = {
            composable(
                route = NoteRoute.NoteListScreen.route,
                content = {
                    NotesListScreen(
                        notesListViewModel = appComponent.noteListViewModel(),
                        navController = navController
                    )
                }
            )
            composable(
                route = NoteRoute.NoteAddScreen.route,
                content = {
                    NoteAddScreen(
                        navController = navController,
                        noteAddViewModel = appComponent.noteAddViewModel()
                    )
                }
            )
        }
    )
}