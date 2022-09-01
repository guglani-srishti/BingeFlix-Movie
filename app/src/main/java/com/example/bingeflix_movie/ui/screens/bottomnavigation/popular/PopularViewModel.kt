package com.example.bingeflix_movie.ui.screens.bottomnavigation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.bingeflix_movie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(repo: MovieRepository) : ViewModel() {
    val popularMovies = repo.popularPagingDataSource().cachedIn(viewModelScope)
}