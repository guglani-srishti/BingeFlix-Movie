package com.example.bingeflix_movie

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bingeflix_movie.ui.screens.bottomnavigation.popular.PopularViewModel

@Composable
fun Popular(
    navController: NavController
) {
    val popularViewModel = hiltViewModel<PopularViewModel>()
    HomeScreen(
        navController = navController,
        movies = popularViewModel.popularMovies
    )
}