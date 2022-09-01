package com.example.bingeflix_movie.data.datasource.database

import androidx.paging.PagingData
import androidx.room.*
import com.example.bingeflix_movie.data.model.MovieItem
import kotlinx.coroutines.flow.Flow
import java.security.cert.CertPathValidatorException.Reason


@Dao
abstract class MoviesDao: BaseDao<MovieItem>() {

    @Query("SELECT * FROM movies")
    // Kotlin flow is an asynchronous stream of values
    abstract fun getPopularMovies(): Flow<List<MovieItem>>// If a new data is inserted with same primary key, It will get replaced by the previous one
    // This ensures that there is always a latest data in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // The fetching of data should NOT be done on the Main thread. Hence coroutine is used
    // If it is executing on one one thread, it may suspend its execution there, and resume in another one
    abstract fun insertMovies(movies: List<MovieItem>)

    // Once the device comes online, the cached data need to be replaced, i.e. delete it
    @Query("DELETE FROM movies")
    abstract fun deleteAllMovies()
}

@Dao
abstract class BaseDao <T> {

}

