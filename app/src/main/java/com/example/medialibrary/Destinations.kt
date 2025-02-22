package com.example.medialibrary

import kotlinx.serialization.Serializable

object Destinations{
    @Serializable class HomeScreen

    //video game
    @Serializable class VideoGamesScreen
    @Serializable class VideoGameModificationScreen
    object VideoGameScreen {
        const val route = "videogame/{id}"
        fun createRoute(id: Int) = "videogame/$id"
    }

    //movie
    @Serializable class MoviesScreen
    @Serializable class MovieModificationScreen
    object MovieScreen {
        const val route = "movie/{id}"
        fun createRoute(id: Int) = "movie/$id"
    }

    //board game
    @Serializable class BoardGameModificationScreen
    @Serializable class BoardGamesScreen
    object BoardGameScreen {
        const val route = "boardgame/{id}"
        fun createRoute(id: Int) = "boardgame/$id"
    }

    //book
    @Serializable class BooksScreen
    @Serializable class BookModificationScreen
    object BookScreen {
        const val route = "book/{id}"
        fun createRoute(id: Int) = "book/$id"
    }
}