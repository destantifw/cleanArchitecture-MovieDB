package com.destanti.MovieDB.data.Model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class VideoDetail (
    val id: Long,
    val results: List<VideoResult>
)


@Parcelize
data class VideoResult (
    val videoId: Int?,
    var movieId: Int?,
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
) : Parcelable


@Entity(tableName = "videoDetailTable")
data class VideoEntity (
    @PrimaryKey(autoGenerate = true)
    val videoId: Int,
    @ColumnInfo(name = "movieId")
    val movieId: Int?,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "iso639_1")
    val iso639_1: String,
    @ColumnInfo(name = "iso3166_1")
    val iso3166_1: String,
    @ColumnInfo(name = "key")
    val key: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "site")
    val site: String,
    @ColumnInfo(name = "size")
    val size: Long,
    @ColumnInfo(name = "type")
    val type: String
)



fun List<VideoEntity>.asVideoList(): List<VideoResult> = this.map {
    VideoResult(it.videoId, it.movieId, it.id, it.iso639_1, it.iso3166_1, it.key, it.name, it.site, it.size, it.type)
}


fun VideoResult.asVideoEntity(): VideoEntity =
    VideoEntity(0, this.movieId, this.id, this.iso639_1, this.iso3166_1, this.key, this.name, this.site, this.size, this.type)
