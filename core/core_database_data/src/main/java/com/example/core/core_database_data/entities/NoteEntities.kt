package com.example.core.core_database_data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Notes"
)
data class NoteEntities(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "note_title") val title:String,
    @ColumnInfo(name = "note_description") val description:String,
    @ColumnInfo(name = "note_date") val date:String,
    @ColumnInfo(name = "note_primary_color", defaultValue = "GREEN") val colorPrimary: String,
    @ColumnInfo(name = "note_secondary_color", defaultValue = "GREEN") val colorSecondary: String,
    @ColumnInfo(name = "note_text_color", defaultValue = "GREEN") val colorText: String,
)