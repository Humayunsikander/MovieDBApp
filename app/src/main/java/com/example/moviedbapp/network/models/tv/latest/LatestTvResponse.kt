package com.example.moviedbapp.network.models.tv.latest

import com.google.gson.annotations.SerializedName

data class LatestTvResponse(
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("created_by") val created_by: List<String>,
    @SerializedName("episode_run_time") val episode_run_time: List<String>,
    @SerializedName("first_air_date") val first_air_date: String,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: Int,
    @SerializedName("in_production") val in_production: Boolean,
    @SerializedName("languages") val languages: List<String>,
    @SerializedName("last_air_date") val last_air_date: String,
    @SerializedName("last_episode_to_air") val last_episode_to_air: LastEpisodeToAir,
    @SerializedName("name") val name: String,
    @SerializedName("next_episode_to_air") val next_episode_to_air: NextEpisodeToAir,
    @SerializedName("networks") val networks: List<String>,
    @SerializedName("number_of_episodes") val number_of_episodes: Int,
    @SerializedName("number_of_seasons") val number_of_seasons: Int,
    @SerializedName("origin_country") val origin_country: List<String>,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_name") val original_name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("production_companies") val production_companies: List<String>,
    @SerializedName("production_countries") val production_countries: List<String>,
    @SerializedName("seasons") val seasons: List<Seasons>,
    @SerializedName("spoken_languages") val spoken_languages: List<String>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("type") val type: String,
    @SerializedName("vote_average") val vote_average: Int,
    @SerializedName("vote_count") val vote_count: Int
)