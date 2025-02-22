package com.example.medialibrary

import kotlinx.serialization.Serializable

object Destinations{
    @Serializable class HomeScreen

    //video game
    @Serializable class VideoGamesScreen

    //movie
    @Serializable class MoviesScreen

    //board game
    @Serializable class BoardGameModificationScreen
    @Serializable class BoardGamesScreen
    object BoardGameScreen { // Changed to object to represent the route
        const val route = "boardgame/{id}" // Define the route with path parameter
        fun createRoute(id: Int) = "boardgame/$id" // Helper function to create route with ID
    }

    //book
    @Serializable class BooksScreen
}