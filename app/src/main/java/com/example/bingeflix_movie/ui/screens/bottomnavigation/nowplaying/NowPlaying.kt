package com.example.bingeflix_movie

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bingeflix_movie.ui.screens.bottomnavigation.nowplaying.NowPlayingViewModel

@Composable
fun NowPlaying(
    navController: NavController,
) {
    val nowPlayViewModel = hiltViewModel<NowPlayingViewModel>()
    HomeScreen(
        navController = navController,
        movies = nowPlayViewModel.popularMovies
    )
}