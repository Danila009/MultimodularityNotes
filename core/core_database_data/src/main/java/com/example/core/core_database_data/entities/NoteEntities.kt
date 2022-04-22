package com.example.core.core_database_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Notes"
)
data class NoteEntities(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title:String,
    val description:String,
    val date:String
)