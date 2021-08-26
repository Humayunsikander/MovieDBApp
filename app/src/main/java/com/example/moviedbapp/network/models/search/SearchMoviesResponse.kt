package com.example.moviedbapp.network.models.search

import com.example.moviedbapp.network.models.movie.MoviesData
import com.google.gson.annotations.SerializedName

data class SearchMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MoviesData>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)