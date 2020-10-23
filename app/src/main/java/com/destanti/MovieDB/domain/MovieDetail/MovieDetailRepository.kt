package com.destanti.MovieDB.domain.MovieDetail

import androidx.lifecycle.LiveData
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.*
import com.destanti.MovieDB.data.remote.MovieDetail.MovieDetailDataSource
import com.destanti.MovieDB.data.remote.NetworkDataSource
import com.g.tragosapp.data.local.LocalDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class MovieDetailRepository @Inject constructor(
    private val networkDataSource: MovieDetailDataSource,
    private val localDataSource: LocalDataSource
) : DefaultMovieDetailRepository {

    override suspend fun getVideoList(movieId: Int): Flow<Resource<List<VideoResult>>> =
        callbackFlow {

            networkDataSource.getVideoList(movieId).collect {
                when(it){
                    is Resource.Success -> {
                        for (video in it.data) {
                            video.movieId = movieId
                            saveVideo(video.asVideoEntity())
                        }
                        offer(getCachedAllVideos(movieId))
                    }
                    is Resource.Failure -> {

                    }
                }
            }
            awaitClose { cancel() }

        }

    override suspend fun saveVideo(video: VideoEntity) {
        localDataSource.saveVideo(video)
    }

    override suspend fun getCachedAllVideos(movieId: Int): Resource<List<VideoResult>> {
        return localDataSource.getCachedVideos(movieId)
    }


}