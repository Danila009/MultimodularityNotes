package com.example.notes.di

import android.content.Context
import androidx.room.Room
import com.example.core.core_database_data.database.NoteDao
import com.example.core.core_database_data.database.NoteDatabase
import com.example.core.core_database_data.repositoryImpl.NoteRepositoryImpl
import com.example.core.core_database_domain.repository.NoteRepository
import com.example.notes.di.scope.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @[Provides AppScope]
    fun providerNoteDao(
        noteDatabase: NoteDatabase
    ):NoteDao = noteDatabase.noteDao()

    @[Provides AppScope]
    fun providerNoteRepository(
        noteDao: NoteDao
    ):NoteRepository = NoteRepositoryImpl(
        noteDao = noteDao
    )

    @[Provides AppScope]
    fun providerNoteDatabase(
        context:Context
    ):NoteDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notes_database"
        ).build()
}