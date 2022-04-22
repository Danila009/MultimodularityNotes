package com.example.core.core_database_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.core_database_data.entities.NoteEntities

@Database(
    version = 1,
    entities = [
        NoteEntities::class
    ]
)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDao
}