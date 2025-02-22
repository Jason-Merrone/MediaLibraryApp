package com.example.medialibrary.models

data class VideoGamePage (
    val id: Int,
    val title: String,
    val developer:String,
    val genre:String,
    val rating:String,
    val platform:String,
    val notes:String
)