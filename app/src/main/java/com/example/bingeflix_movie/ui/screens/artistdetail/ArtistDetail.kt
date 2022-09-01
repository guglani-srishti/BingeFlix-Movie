package com.example.bingeflix_movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.R
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.bingeflix_movie.data.datasource.ApiURL
import com.example.bingeflix_movie.data.model.artist.ArtistDetail
import com.example.bingeflix_movie.navigation.NavigationScreen
import com.example.bingeflix_movie.ui.screens.artistdetail.ArtistDetailViewModel
import com.example.bingeflix_movie.utils.network.DataState
import com.example.bingeflix_movie.R

@Composable
fun ArtistDetail(personId: Int) {
    val artistDetailViewModel = hiltViewModel<ArtistDetailViewModel>()
    val artistDetail = artistDetailViewModel.artistDetail
    val progressBar = remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        artistDetailViewModel.artistDetail(personId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                defaultBackgroundColor
            )
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)

        artistDetail.value.let {
            if (it is DataState.Success<ArtistDetail>) {
                Row() {
                    Image(
                        painter = rememberImagePainter(ApiURL.IMAGE_URL.plus(it.data.profilePath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .height(250.dp)
                            .width(190.dp)
                            .cornerRadius10()
                    )
                    Column {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = it.data.name,
                            color = FontColor,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Medium
                        )
                        PersonalInfo(stringResource(R.string.know_for), it.data.knownForDepartment)
                        PersonalInfo(
                            stringResource(R.string.gender),
                            it.data.gender.genderInString()
                        )
                        PersonalInfo(stringResource(R.string.birth_day), it.data.birthday)
                        PersonalInfo(stringResource(R.string.place_of_birth), it.data.placeOfBirth)
                    }
                }
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = stringResource(R.string.biography),
                    color = secondaryFontColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
                BioGraphyText(
                    text = it.data.biography
                )
            }
        }
    }

    artistDetail.pagingLoadingState {
        progressBar.value = it
    }
}

@Composable
fun PersonalInfo(title: String, info: String) {
    Column(modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)) {
        Text(
            text = title,
            color = secondaryFontColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = info,
            color = FontColor,
            fontSize = 16.sp
        )
    }
}