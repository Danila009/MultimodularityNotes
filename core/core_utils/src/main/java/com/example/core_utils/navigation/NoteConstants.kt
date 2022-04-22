package com.example.core_utils.navigation

object NoteConstants {
    const val NOTES_ROUTE = "notes_route"
    const val NOTE_ID_ARGUMENT = "noteId"
}

sealed class NoteRoute(val route:String){
    object NoteListScreen: NoteRoute(route = "notes_list_screen")
    object NoteAddScreen: NoteRoute(route = "note_add_screen")
    object NoteInfoScreen: NoteRoute(route = "note_info_screen?noteId={noteId}"){
        fun data(
            noteId:Int
        ):String = "note_info_screen?noteId=$noteId"
    }
}