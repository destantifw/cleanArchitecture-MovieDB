package com.destanti.MovieDB.domain.MovieDetail

import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.MovieEntity
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.data.Model.VideoEntity
import com.destanti.MovieDB.data.Model.VideoResult
import kotlinx.coroutines.flow.Flow

interface DefaultMovieDetailRepository {

    suspend fun getVideoList(movieId: Int): Flow<Resource<List<VideoResult>>>
    suspend fun saveVideo(movie: VideoEntity)
    suspend fun getCachedAllVideos(movieId: Int): Resource<List<VideoResult>>
}