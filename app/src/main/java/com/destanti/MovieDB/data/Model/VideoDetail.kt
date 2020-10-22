package com.destanti.MovieDB.data.Model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*

data class VideoDetail (
    val id: Long,
    val results: List<Result>
)

data class Result (
    val id: String,

    @SerializedName("iso_639_1")
    val iso639_1: String,

    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    val key: String,
    val name: String,
    val site: String,
    val size: Long,
    val type: String
)
