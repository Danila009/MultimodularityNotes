package com.example.feature_note_add.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.core.core_database_data.database.NoteDao
import com.example.core_utils.di.annotation.Feature
import com.example.feature_note_add.NoteAddViewModel
import dagger.Component
import kotlin.properties.Delegates.notNull

@[Feature Component(dependencies = [NoteAddDeps::class])]
internal interface NoteAddComponent {
    fun noteAddViewModel(): NoteAddViewModel

    @Component.Builder
    interface Builder{

        fun deps(noteAddDeps: NoteAddDeps):Builder

        fun build():NoteAddComponent
    }
}

interface NoteAddDeps {
    val noteDao:NoteDao
}

interface NoteAddDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: NoteAddDeps

    companion object : NoteAddDepsProvider by NoteAddDepsStore
}

object NoteAddDepsStore : NoteAddDepsProvider {

    override var deps: NoteAddDeps by notNull()
}

internal class NoteAddComponentViewModel : ViewModel() {

//    val newDetailsComponent =
//        DaggerNoteComponent.builder().deps(ArticlesDepsProvider.deps).build()
}