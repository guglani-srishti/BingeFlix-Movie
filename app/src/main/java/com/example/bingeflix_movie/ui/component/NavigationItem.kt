package com.example.bingeflix_movie.ui.component

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bingeflix_movie.navigation.NavigationScreen

sealed class NavigationItem(
    var route: String,
    var icon: @Composable () -> Unit,
    var title: String
) {
    object Home : NavigationItem(NavigationScreen.HOME, {
        Icon(
            Icons.Filled.Home,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    }, "Home")

    object Popular : NavigationItem(NavigationScreen.POPULAR, {
        Icon(
            Icons.Filled.Timeline,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    }, "Popular")

    object TopRated : NavigationItem(NavigationScreen.TOP_RATED, {
        Icon(
            Icons.Filled.Star,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    }, "Favorites")

    object Upcoming : NavigationItem(NavigationScreen.UP_COMING, {
        Icon(
            Icons.Filled.KeyboardArrowDown,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    }, "Up coming")
}