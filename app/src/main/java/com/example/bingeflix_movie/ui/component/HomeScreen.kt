package com.example.bingeflix_movie

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.bingeflix_movie.data.datasource.ApiURL
import com.example.bingeflix_movie.data.model.MovieItem
import com.example.bingeflix_movie.navigation.NavigationScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    navController: NavController,
    movies: Flow<PagingData<MovieItem>>
) {
    val activity = (LocalContext.current as? Activity)
    val progressBar = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    val moviesItems: LazyPagingItems<MovieItem> = movies.collectAsLazyPagingItems()

    BackHandler(enabled = (currentRoute(navController) == NavigationScreen.HOME)) {
        // execute your custom logic here
        openDialog.value = true
    }
    Column(modifier = Modifier.background(defaultBackgroundColor)) {
        Column(modifier = Modifier.background(defaultBackgroundColor)) {
            CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp),
                content = {
                    items(moviesItems) { item ->
                        item?.let {
                            MovieItemView(item, navController)
                        }
                    }
                })

        }
        if (openDialog.value) {
            ExitAlertDialog(navController, {
                openDialog.value = it
            }, {
                activity?.finish()
            })
        }
    }
    moviesItems.pagingLoadingState {
        progressBar.value = it
    }
}

@Composable
fun MovieItemView(item: MovieItem, navController: NavController) {
    Column(modifier = Modifier.padding(5.dp)) {
        Image(
            painter = rememberImagePainter(ApiURL.IMAGE_URL.plus(item.posterPath)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .cornerRadius10()
                .clickable {
                    navController.navigate(NavigationScreen.MovieDetail.MOVIE_DETAIL.plus("/${item.id}"))
                }
        )
    }
}