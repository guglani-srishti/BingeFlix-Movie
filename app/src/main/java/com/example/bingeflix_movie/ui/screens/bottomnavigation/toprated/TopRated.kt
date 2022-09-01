package com.example.bingeflix_movie

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bingeflix_movie.ui.screens.bottomnavigation.toprated.TopRatedViewModel

@Composable
fun TopRated(
    navController: NavController,
) {
    val topRatedViewModel = hiltViewModel<TopRatedViewModel>()
    HomeScreen(
        navController = navController,
        movies = topRatedViewModel.topRatedMovies
    )
}