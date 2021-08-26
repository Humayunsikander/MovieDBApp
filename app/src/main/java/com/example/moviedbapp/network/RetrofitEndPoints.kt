package com.example.moviedbapp.network

import com.example.moviedbapp.network.models.movie.NowPlayingMoviesResponse
import com.example.moviedbapp.network.models.movie.PopularMoviesResponse
import com.example.moviedbapp.network.models.movie.TopRatedMoviesResponse
import com.example.moviedbapp.network.models.movie.UpComingMoviesResponse
import com.example.moviedbapp.network.models.movie.details.MovieDetailsResponse
import com.example.moviedbapp.network.models.search.SearchMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitEndPoints {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMoviesAsync(
        @Query("api_key") apiKey: String
    ): Response<NowPlayingMoviesResponse>?

    @GET("movie/popular")
    suspend fun getPopularMoviesAsync(
        @Query("api_key") apiKey: String
    ): Response<PopularMoviesResponse>?

    @GET("movie/top_rated")
    suspend fun getTopRatedMoviesAsync(
        @Query("api_key") apiKey: String
    ): Response<TopRatedMoviesResponse>?

    @GET("movie/upcoming")
    suspend fun getUpComingMoviesAsync(
        @Query("api_key") apiKey: String
    ): Response<UpComingMoviesResponse>?

    @GET("search/movie")
    suspend fun getSearchedMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<SearchMoviesResponse>?

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsAsync(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse>?


    /*  @GET("discover/tv/")
      suspend fun getPopularTvShowsAsync(
          @Query("sort_by") name: String
      ): Deferred<PopularTvShowsResponse>

      @GET("search/movie")
      suspend fun searchMovieAsync(
          @Query("query") query: String
      ): Deferred<SearchMoviesResponse>

      @GET("/genre/movie/list")
      suspend fun getMoviesGenresAsync(

      ): Deferred<MoviesGenresListResponse>

      @GET("movie/{movie_id}")
      suspend fun getMovieDetailsAsync(
          @Path("movie_id") movie_id: Int
      ): Deferred<MovieDetailResponseDto>

      @GET("movie/{movie_id}/similar")
      suspend fun getSimilarMoviesAsync(
          @Path("movie_id") movie_id: Int
      ): Deferred<SimilarMoviesResponse>
  */

}