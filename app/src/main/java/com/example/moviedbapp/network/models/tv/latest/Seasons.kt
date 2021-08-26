package com.example.moviedbapp.network.models.tv.latest

import com.google.gson.annotations.SerializedName

data class Seasons(
    @SerializedName("air_date") val air_date: String,
    @SerializedName("episode_count") val episode_count: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("season_number") val season_number: Int
)