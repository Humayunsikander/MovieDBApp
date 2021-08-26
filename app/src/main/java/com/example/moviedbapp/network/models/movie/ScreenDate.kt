package com.example.moviedbapp.network.models.movie

import com.google.gson.annotations.SerializedName

data class ScreenDate(
    @SerializedName("maximum") val maximum: String,
    @SerializedName("minimum") val minimum: String
)