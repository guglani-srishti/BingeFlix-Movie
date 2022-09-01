package com.example.bingeflix_movie.data.datasource

object ApiURL {
    private const val API_KEY = "4e7f0b85767833a66b68b9fcd223fb6e"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    const val MOVIE_LIST = "movie/now_playing?api_key=$API_KEY&language=en-US"
    const val POPULAR_MOVIE_LIST = "movie/popular?api_key=$API_KEY&language=en-US"
    const val TOP_RATED_MOVIE_LIST = "movie/top_rated?api_key=$API_KEY&language=en-US"
    const val UP_COMING_MOVIE_LIST = "movie/upcoming?api_key=$API_KEY&language=en-US"
    const val MOVIE_DETAIL ="movie/{movieId}?api_key=$API_KEY&language=en-US"
    const val RECOMMENDED_MOVIE ="movie/{movieId}/recommendations?api_key=$API_KEY&language=en-US"
    const val SEARCH_MOVIE ="search/movie?api_key=$API_KEY&language=en-US&page=1&include_adult=false"
    const val GENRE_LIST ="genre/movie/list?api_key=$API_KEY&language=en-US"
    const val GENRE_MOVIES_BY_ID ="discover/movie?api_key=$API_KEY&language=en-US"
    const val MOVIE_CREDIT ="movie/{movieId}/credits?api_key=$API_KEY&language=en-US"
    const val ARTIST_DETAIL ="person/{personId}?api_key=$API_KEY&language=en-US"
}