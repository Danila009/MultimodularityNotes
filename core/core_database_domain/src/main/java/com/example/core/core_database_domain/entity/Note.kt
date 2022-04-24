package com.example.core.core_database_domain.entity

data class Note(
    val id:Int = 0,
    var title:String = "",
    var description:String = "",
    val date:String = "",
    var colorPrimary:String = "PRIMARY",
    var colorSecondary: String = "SECONDARY",
    var colorText:String = "WHERE"
)