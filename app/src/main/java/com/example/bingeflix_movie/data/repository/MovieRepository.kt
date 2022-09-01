package com.example.bingeflix_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.example.bingeflix_movie.data.datasource.ApiService
import com.example.bingeflix_movie.data.datasource.database.MovieListDatabase
import com.example.bingeflix_movie.data.datasource.paging.*
import com.example.bingeflix_movie.data.model.BaseModel
import com.example.bingeflix_movie.data.model.Genres
import com.example.bingeflix_movie.data.model.MovieItem
import com.example.bingeflix_movie.data.model.artist.Artist
import com.example.bingeflix_movie.data.model.artist.ArtistDetail
import com.example.bingeflix_movie.data.model.moviedetail.MovieDetail
import com.example.bingeflix_movie.helper.networkBoundResource
import com.example.bingeflix_movie.utils.network.DataState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService, private val db: MovieListDatabase
) {

    suspend fun movieList(page: Int): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.nowPlayingMovieList(page)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.recommendedMovie(movieId, page)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }


    suspend fun search(searchKey: String): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.search(searchKey)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun genreList(): Flow<DataState<Genres>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.genreList()
            emit(DataState.Success(genreResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun moviesByGenreId(page: Int, genreId: String): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.moviesByGenre(page, genreId)
            emit(DataState.Success(genreResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.movieCredit(movieId)
            emit(DataState.Success(artistResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>> = flow {
        emit(DataState.Loading)
        try {
            val artistDetailResult = apiService.artistDetail(personId)
            emit(DataState.Success(artistDetailResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    fun nowPlayingPagingDataSource() = Pager(
        pagingSourceFactory = { NowPlayingPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    val moviesDao = db.moviesDao()
    fun popularPagingDataSource(): Flow<PagingData<MovieItem>> {
        networkBoundResource(
                query = {
                    moviesDao.getPopularMovies()
                },
                fetch = {
                    Pager(
                        pagingSourceFactory = { PopularPagingDataSource(apiService) },
                        config = PagingConfig(pageSize = 1)
                    ).flow
                },
                saveFetchResult = { PopularMovieList ->
                    db.withTransaction {
                        PopularMovieList.collect {
                            moviesDao.deleteAllMovies()
                            moviesDao.insertMovies(it.toList())
                        }

                    }
                }
        )
        return Pager(
                pagingSourceFactory = { PopularPagingDataSource(apiService) },
                config = PagingConfig(pageSize = 1)
            ).flow
    }


    fun topRatedPagingDataSource() = Pager(
        pagingSourceFactory = { TopRatedPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun upcomingPagingDataSource() = Pager(
        pagingSourceFactory = { UpcomingPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun genrePagingDataSource(genreId: String) = Pager(
        pagingSourceFactory = { GenrePagingDataSource(apiService, genreId) },
        config = PagingConfig(pageSize = 1)
    ).flow


}

@Suppress("UNCHECKED_CAST")

private suspend fun <T : Any> PagingData<T>.toList(): List<T> {

    val flow = PagingData::class.java.getDeclaredField("flow").apply {

        isAccessible = true

    }.get(this) as Flow<Any?>

    val pageEventInsert = flow.single()

    val pageEventInsertClass = Class.forName("androidx.paging.PageEvent\$Insert")

    val pagesField = pageEventInsertClass.getDeclaredField("pages").apply {

        isAccessible = true

    }

    val pages = pagesField.get(pageEventInsert) as List<Any?>

    val transformablePageDataField =

        Class.forName("androidx.paging.TransformablePage").getDeclaredField("data").apply {

            isAccessible = true

        }

    val listItems =

        pages.flatMap { transformablePageDataField.get(it) as List<*> }

    return listItems as List<T>

}