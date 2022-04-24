package com.example.core.core_database_data.database

import androidx.room.*
import com.example.core.core_database_data.entities.NoteEntities
import com.example.core.core_database_domain.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteEntities: NoteEntities)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateNote(noteEntities: NoteEntities)

    @Query("DELETE  FROM notes WHERE id = :id")
    suspend fun deleteNote(id:Int)

    @Query("SELECT * FROM notes WHERE note_title LIKE '%' || :search || '%'")
    fun realmAddNote(search:String?):Flow<List<NoteEntities>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun realmNoteFlow(id:Int):Flow<NoteEntities>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun realmNote(id: Int):NoteEntities

    @Query("SELECT COUNT(id) FROM notes")
    fun noteCount():Flow<Int>
}