package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.feature_notes_list.NotesListScreen
import com.example.notes.di.AppComponent
import com.example.notes.di.DaggerAppComponent

class MainActivity : ComponentActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
        setContent {
            NotesListScreen(
                notesListViewModel = appComponent.noteListViewModel()
            )
        }
    }
}
