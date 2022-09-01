package com.example.bingeflix_movie.ui.screens.artistdetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bingeflix_movie.data.model.artist.ArtistDetail
import com.example.bingeflix_movie.data.repository.MovieRepository
import com.example.bingeflix_movie.navigation.NavigationScreen
import com.example.bingeflix_movie.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {
    val artistDetail: MutableState<DataState<ArtistDetail>?> = mutableStateOf(null)

    fun artistDetail(personId: Int) {
        viewModelScope.launch {
            repo.artistDetail(personId).onEach {
                artistDetail.value = it
            }.launchIn(viewModelScope)
        }
    }
}