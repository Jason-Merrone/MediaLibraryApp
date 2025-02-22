package com.example.medialibrary

import android.annotation.SuppressLint
import com.example.medialibrary.ui.screens.boardgamescreens.BoardGamesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.medialibrary.ui.theme.MediaLibraryTheme
import com.example.medialibrary.viewmodels.boardgameviewmodels.BoardGamesScreenViewModel
import com.example.medialibrary.ui.screens.boardgamescreens.BoardGameModificationScreen
import com.example.medialibrary.ui.screens.boardgamescreens.BoardGameScreen
import com.example.medialibrary.ui.screens.bookscreens.BookModificationScreen
import com.example.medialibrary.ui.screens.bookscreens.BookScreen
import com.example.medialibrary.ui.screens.bookscreens.BooksScreen
import com.example.medialibrary.ui.screens.moviescreens.MovieModificationScreen
import com.example.medialibrary.ui.screens.moviescreens.MovieScreen
import com.example.medialibrary.ui.screens.moviescreens.MoviesScreen
import com.example.medialibrary.ui.screens.videogamescreens.VideoGameModificationScreen
import com.example.medialibrary.ui.screens.videogamescreens.VideoGameScreen
import com.example.medialibrary.ui.screens.videogamescreens.VideoGamesScreen
import com.example.medialibrary.viewmodels.bookviewmodels.BooksScreenViewModel
import com.example.medialibrary.viewmodels.movieviewmodels.MoviesScreenViewModel
import com.example.medialibrary.viewmodels.videogameviewmodels.VideoGamesScreenViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val boardGamesViewModel by viewModels<BoardGamesScreenViewModel>()
            val booksViewModel by viewModels<BooksScreenViewModel>()
            val moviesViewModel by viewModels<MoviesScreenViewModel>()
            val videoGamesViewModel by viewModels<VideoGamesScreenViewModel>()

            MediaLibraryTheme {
                Box(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(16.dp, 32.dp)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.HomeScreen()
                    ) {
                        composable<Destinations.HomeScreen> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(24.dp)
                            ) {
                                // Header added to the homepage
                                Text(
                                    text = "Media Library",
                                    style = MaterialTheme.typography.headlineLarge
                                )
                                Button(onClick = {
                                    navController.navigate(Destinations.VideoGamesScreen())
                                }) {
                                    Text("Video Games (" + videoGamesViewModel.videoGames.value.size+")")
                                }
                                Button(onClick = {
                                    navController.navigate(Destinations.MoviesScreen())
                                }) {
                                    Text("Movies (" + moviesViewModel.movies.value.size+")")
                                }
                                Button(onClick = {
                                    navController.navigate(Destinations.BoardGamesScreen())
                                }) {
                                    Text("Board Games (" + boardGamesViewModel.boardGames.value.size+")")
                                }
                                Button(onClick = {
                                    navController.navigate(Destinations.BooksScreen())
                                }) {
                                    Text("Books (" + booksViewModel.books.value.size+")")
                                }
                            }
                        }

                        // Video Game Screens
                        composable<Destinations.VideoGamesScreen> {
                            VideoGamesScreen(
                                gotToVideoGameModification = {
                                    navController.navigate(Destinations.VideoGameModificationScreen())
                                },
                                goToVideoGame = { id ->
                                    navController.navigate(Destinations.VideoGameScreen.createRoute(id))
                                },
                                viewModel = videoGamesViewModel
                            )
                        }
                        composable(Destinations.VideoGameScreen.route) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
                            VideoGameScreen(
                                viewModel = VideoGamesScreenViewModel(),
                                id = id
                            )
                        }
                        dialog<Destinations.VideoGameModificationScreen> {
                            VideoGameModificationScreen(
                                id = null,
                                goBack = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // Movie Screens
                        composable<Destinations.MoviesScreen> {
                            MoviesScreen(
                                gotToMovieModification = {
                                    navController.navigate(Destinations.MovieModificationScreen())
                                },
                                goToMovie = { id ->
                                    navController.navigate(Destinations.MovieScreen.createRoute(id))
                                },
                                viewModel = moviesViewModel
                            )
                        }
                        composable(Destinations.MovieScreen.route) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
                            MovieScreen(
                                viewModel = MoviesScreenViewModel(),
                                id = id
                            )
                        }
                        dialog<Destinations.MovieModificationScreen> {
                            MovieModificationScreen(
                                id = null,
                                goBack = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // Board Game Screens
                        composable<Destinations.BoardGamesScreen> {
                            BoardGamesScreen(
                                gotToBoardGameModification = {
                                    navController.navigate(Destinations.BoardGameModificationScreen())
                                },
                                goToBoardGame = { id ->
                                    navController.navigate(Destinations.BoardGameScreen.createRoute(id))
                                },
                                viewModel = boardGamesViewModel
                            )
                        }
                        composable(Destinations.BoardGameScreen.route) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
                            BoardGameScreen(
                                viewModel = BoardGamesScreenViewModel(),
                                id = id
                            )
                        }

                        dialog<Destinations.BoardGameModificationScreen> {
                            BoardGameModificationScreen(
                                id = null,
                                goBack = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // Book Screens
                        composable<Destinations.BooksScreen> {
                            BooksScreen(
                                gotToBookModification = {
                                    navController.navigate(Destinations.BookModificationScreen())
                                },
                                goToBook = { id ->
                                    navController.navigate(Destinations.BookScreen.createRoute(id))
                                },
                                viewModel = booksViewModel
                            )
                        }
                        composable(Destinations.BookScreen.route) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
                            BookScreen(
                                viewModel = BooksScreenViewModel(),
                                id = id
                            )
                        }
                        dialog<Destinations.BookModificationScreen> {
                            BookModificationScreen(
                                id = null,
                                goBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}