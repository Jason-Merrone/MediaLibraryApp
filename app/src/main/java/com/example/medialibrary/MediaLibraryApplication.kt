package com.example.medialibrary

import android.app.Application
import com.example.medialibrary.repositories.BoardGamesRepository
import com.example.medialibrary.repositories.BooksRepository
import com.example.medialibrary.repositories.MoviesRepository
import com.example.medialibrary.repositories.VideoGamesRepository

class MediaLibraryApplication : Application(){
    val boardGamesRepository = BoardGamesRepository
    val booksRepository = BooksRepository
    val moviesRepository = MoviesRepository
    val videoGamesRepository = VideoGamesRepository
}