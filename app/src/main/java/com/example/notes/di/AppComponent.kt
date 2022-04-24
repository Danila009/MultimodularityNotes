package com.example.notes.di

import android.content.Context
import com.example.core.core_database_data.database.NoteDao
import com.example.feature_note_add.di.NoteAddDeps
import com.example.feature_note_update.viewModel.NoteUpdateViewModel
import com.example.feature_notes_info.viewModel.NoteInfoViewModel
import com.example.feature_notes_list.NotesListViewModel
import com.example.notes.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(modules = [DatabaseModule::class])]
interface AppComponent: NoteAddDeps {

    override val noteDao:NoteDao

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