package com.destanti.MovieDB.data.Model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

data class MovieDetailModel (
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: String? = null,

    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,

    @SerializedName("imdb_id")
    val imdbID: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,

    @SerializedName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)

data class ProductionCompany (
    val id: Long,

    @SerializedName("logo_path")
    val logoPath: Any? = null,

    val name: String,

    @SerializedName("origin_country")
    val originCountry: String
)

data class ProductionCountry (
    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    val name: String
)

data class SpokenLanguage (
    @SerializedName("iso_639_1")
    val iso639_1: String,

    val name: String
)

@Parcelize
data class Genres (
    val genres: List<Genre>
) : Parcelable

@Parcelize
data class Genre (
    val genreId: Int?,
    val id: Long,
    val name: String
) : Parcelable

@Entity(tableName = "genreListTable")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    val genreId: Int,
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String
)


fun List<GenreEntity>.asGenreList(): List<Genre> = this.map {
    Genre(it.genreId, it.id, it.name)
}

fun Genre.asGenreEntity(): GenreEntity =  GenreEntity(0,this.id, this.name)
