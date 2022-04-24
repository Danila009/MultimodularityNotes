package com.example.notes.di

import android.content.Context
import com.example.feature_note_add.NoteAddViewModel
import com.example.feature_note_update.viewModel.NoteUpdateViewModel
import com.example.feature_notes_info.viewModel.NoteInfoViewModel
import com.example.feature_notes_list.NotesListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@[Singleton Component(modules = [DatabaseModule::class])]
interface AppComponent {

    fun noteAddViewModel():NoteAddViewModel
    fun noteListViewModel():NotesListViewModel
    fun noteInfoViewModel():NoteInfoViewModel
    fun noteUpdateViewModel():NoteUpdateViewModel

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context):Builder

        fun build():AppComponent
    }
}