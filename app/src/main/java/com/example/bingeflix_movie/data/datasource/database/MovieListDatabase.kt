package com.example.bingeflix_movie.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bingeflix_movie.data.model.MovieItem
import com.example.bingeflix_movie.utils.roomTypeConverters.GenreConverter

@Database(entities = [MovieItem::class], version = 1)
@TypeConverters(GenreConverter::class)
abstract class MovieListDatabase : RoomDatabase(){
    abstract fun moviesDao(): MoviesDao
}