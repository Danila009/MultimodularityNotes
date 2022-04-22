package com.example.core.core_database_domain.entity

data class Note(
    val id:Int = 0,
    val title:String = "",
    val description:String = "",
    val date:String = "",
    val colorPrimary:String = "PRIMARY",
    val colorSecondary: String = "SECONDARY",
    val colorText:String = "WHERE"
)