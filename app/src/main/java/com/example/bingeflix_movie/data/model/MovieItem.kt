package com.example.bingeflix_movie.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bingeflix_movie.utils.roomTypeConverters.GenreConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieItem (
    @SerializedName("adult")
    @ColumnInfo(name = "adult") val adult: Boolean,
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdropPath") val backdropPath: String,
    @SerializedName("genre_ids")
    @TypeConverters(GenreConverter::class)
    @ColumnInfo(name = "genreIds") val genreIds: ArrayList<Int>,
    @SerializedName("id")
    @PrimaryKey val id: Int,
    @SerializedName("original_language")
    @ColumnInfo(name = "originalLanguage") val originalLanguage: String,
    @SerializedName("original_title")
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @SerializedName("overview")
    @ColumnInfo(name = "overview") val overview: String,
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity") val popularity: Double,
    @SerializedName("poster_path")
    @ColumnInfo(name = "posterPath") val posterPath: String,
    @SerializedName("release_date")
    @ColumnInfo(name = "releaseDate")val releaseDate: String,
    @SerializedName("title")
    @ColumnInfo(name = "title") val title: String,
    @SerializedName("video")
    @ColumnInfo(name = "video") val video: Boolean,
    @SerializedName("vote_average")
    @ColumnInfo(name = "voteAverage") val voteAverage: Double,
    @SerializedName("vote_count")
    @ColumnInfo(name = "voteCount") val voteCount: Int
)
