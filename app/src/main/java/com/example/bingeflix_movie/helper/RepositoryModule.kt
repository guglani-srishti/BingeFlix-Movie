package com.example.bingeflix_movie.helper

import com.example.bingeflix_movie.data.datasource.ApiService
import com.example.bingeflix_movie.data.datasource.database.MovieListDatabase
import com.example.bingeflix_movie.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideMovieRepository(
        apiService: ApiService, db: MovieListDatabase
    ): MovieRepository {
        return MovieRepository(
            apiService, db
        )
    }

}