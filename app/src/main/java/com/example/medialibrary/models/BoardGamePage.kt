package com.example.medialibrary.models

data class BoardGamePage (
    val id: Int,
    val title: String,
    val minPlayers:Int,
    val maxPlayers:Int,
    val type:String,
    val notes:String
)