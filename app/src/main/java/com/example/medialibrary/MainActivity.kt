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

class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val boardGamesViewModel by viewModels<BoardGamesScreenViewModel>()

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
                                    Text("Video Games")
                                }
                                Button(onClick = {
                                    navController.navigate(Destinations.MoviesScreen())
                                }) {
                                    Text("Movies")
                                }
                                Button(onClick = {
                                    navController.navigate(Destinations.BoardGamesScreen())
                                }) {
                                    Text("Board Games " + boardGamesViewModel.boardGames.value.size)
                                }
                                Button(onClick = {
                                    navController.navigate(Destinations.BooksScreen())
                                }) {
                                    Text("Books")
                                }
                            }
                        }

                        // Video Game Screens
                        composable<Destinations.VideoGamesScreen> {
                            Button(onClick = {
                                navController.popBackStack()
                            }) {
                                Text("Go back to page 1")
                            }
                        }

                        // Movie Screens
                        composable<Destinations.MoviesScreen> {
                            Button(onClick = {
                                navController.popBackStack()
                            }) {
                                Text("Go back to page 1")
                            }
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
                                viewModel = BoardGamesScreenViewModel()
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
                            Button(onClick = {
                                navController.popBackStack()
                            }) {
                                Text("Go back to page 1")
                            }
                        }
                    }
                }
            }
        }
    }
}
