package com.example.notes.di

import android.content.Context
import com.example.feature_notes_list.NotesListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DatabaseModule::class
    ]
)
@Singleton
interface AppComponent {

    fun noteListViewModel():NotesListViewModel

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context):Builder

        fun build():AppComponent
    }
}