package com.example.medialibrary.models

data class BookPage (
    val id: Int,
    val title: String,
    val author:String,
    val format:String,
    val numPages:Int,
    val genre:String,
    val notes:String
)