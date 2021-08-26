package com.example.moviedbapp.network.models.tv.on_the_air

import com.example.moviedbapp.network.models.tv.TvData
import com.google.gson.annotations.SerializedName

data class OnTheAirTvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvData>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)