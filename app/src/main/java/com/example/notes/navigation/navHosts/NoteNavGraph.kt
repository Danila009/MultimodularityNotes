package com.example.notes.navigation.navHosts

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.core_utils.navigation.NoteConstants.NOTES_ROUTE
import com.example.core_utils.navigation.NoteConstants.NOTE_ID_ARGUMENT
import com.example.core_utils.navigation.NoteRoute
import com.example.feature_note_add.NoteAddScreen
import com.example.feature_notes_info.NoteInfoScreen
import com.example.feature_notes_list.NotesListScreen
import com.example.notes.di.AppComponent

@ExperimentalFoundationApi
@ExperimentalMaterialApi
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
            composable(
                route = NoteRoute.NoteInfoScreen.route,
                arguments = listOf(
                    navArgument(
                        name = NOTE_ID_ARGUMENT
                    ){
                        type = NavType.IntType
                    }
                ),
                content = {
                    NoteInfoScreen(
                        noteInfoViewModel = appComponent.noteInfoViewModel(),
                        navController = navController,
                        idNote = it.arguments!!.getInt(NOTE_ID_ARGUMENT)
                    )
                }
            )
        }
    )
}