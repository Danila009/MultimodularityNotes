package com.example.core.core_database_data.database

import androidx.room.*
import com.example.core.core_database_data.entities.NoteEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteEntities: NoteEntities)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateNote(noteEntities: NoteEntities)

    @Delete
    suspend fun deleteNote(noteEntities: NoteEntities)

    @Query("SELECT * FROM notes")
    fun realmAddNote():Flow<List<NoteEntities>>

    @Query("SELECT * FROM notes WHERE id == :id")
    fun realmNote(id:Int):Flow<NoteEntities>
}