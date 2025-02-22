package com.example.medialibrary.models

data class MoviePage (
    val id: Int,
    val title: String,
    val genre:String,
    val rating:String,
    val runtime:String,
    val format:String,
    val notes:String
)