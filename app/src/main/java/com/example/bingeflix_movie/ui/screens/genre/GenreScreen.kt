package com.example.bingeflix_movie

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bingeflix_movie.ui.screens.genre.GenreViewModel

@Composable
fun GenreScreen(
    navController: NavController,
    genreId: String
) {
    val genreViewModel = hiltViewModel<GenreViewModel>()
    HomeScreen(
        navController = navController,
        movies = genreViewModel.moviesByGenre(genreId)
    )
}