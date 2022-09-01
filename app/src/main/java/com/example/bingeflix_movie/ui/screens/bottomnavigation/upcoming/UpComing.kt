package com.example.bingeflix_movie

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bingeflix_movie.ui.screens.bottomnavigation.upcoming.UpComingViewModel

@Composable
fun Upcoming(
    navController: NavController,
) {
    val upComingViewModel = hiltViewModel<UpComingViewModel>()
    HomeScreen(
        navController = navController,
        movies = upComingViewModel.upcomingMovies
    )
}