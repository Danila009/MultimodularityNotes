package com.example.core_utils.navigation

object NoteConstants {
    const val NOTES_ROUTE = "notes_route"
}

sealed class NoteRoute(val route:String){
    object NoteListScreen: NoteRoute(route = "notes_list_screen")
    object NoteAddScreen: NoteRoute(route = "note_add_screen")
}