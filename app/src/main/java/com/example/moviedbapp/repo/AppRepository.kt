package com.example.moviedbapp.repo

import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.network.RetrofitEndPoints

class AppRepository(
    private val retrofitEndPoints: RetrofitEndPoints
) {


    suspend fun getNowPlayingMovies(): retrofit2.Response<*>? {
        return retrofitEndPoints.getNowPlayingMoviesAsync(BuildConfig.API_KEY)
    }

    suspend fun getPopularMovies(): retrofit2.Response<*>? {
        return retrofitEndPoints.getPopularMoviesAsync(BuildConfig.API_KEY)
    }

    suspend fun getTopRatedMovies(): retrofit2.Response<*>? {
        return retrofitEndPoints.getTopRatedMoviesAsync(BuildConfig.API_KEY)
    }

    suspend fun getUpComingMovies(): retrofit2.Response<*>? {
        return retrofitEndPoints.getUpComingMoviesAsync(BuildConfig.API_KEY)
    }

    suspend fun searchMovie(keyword: String): retrofit2.Response<*>? {
        return retrofitEndPoints.getSearchedMoviesAsync(BuildConfig.API_KEY, keyword)
    }

    suspend fun getMovieDetails(movieId: Int): retrofit2.Response<*>? {
        return retrofitEndPoints.getMovieDetailsAsync(movieId, BuildConfig.API_KEY)
    }

}