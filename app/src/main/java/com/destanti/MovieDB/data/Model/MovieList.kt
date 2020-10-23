package com.destanti.MovieDB.data.Model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MovieList (
    val page: Int,
    val results: List<MovieListResult> = listOf(),

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int
)

@Parcelize
data class MovieListResult (
    val movieId: Int?,
    @SerializedName("poster_path")
    val posterPath: String,

    val adult: Boolean,
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,


    val id: Int,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    val title: String,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    val popularity: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double
) : Parcelable

@Entity(tableName = "movieListTable")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double
)


fun List<MovieEntity>.asMovieList(): List<MovieListResult> = this.map {
    MovieListResult(it.movieId, it.posterPath, it.adult, it.overview, it.releaseDate, it.id, it.originalTitle,
        it.originalLanguage, it.title, it.backdropPath, it.popularity, it.voteCount, it.video, it.voteAverage)
}


fun MovieListResult.asMovieEntity(): MovieEntity =
    MovieEntity(0,this.id, this.posterPath, this.adult, this.overview, this.releaseDate, this.originalTitle, this.originalLanguage,
    this.title, this.backdropPath, this.popularity, this.voteCount, this.video, this.voteAverage)
