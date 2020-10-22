package com.destanti.MovieDB.data.Model

import com.google.gson.annotations.SerializedName


data class ReviewModel (
    val id: Long,
    val page: Long,
    val results: List<ReviewResult>,

    @SerializedName("total_pages")
    val totalPages: Long,

    @SerializedName("total_results")
    val totalResults: Long
)

data class ReviewResult (
    val id: String,
    val author: String,
    val content: String,
    val url: String
)
