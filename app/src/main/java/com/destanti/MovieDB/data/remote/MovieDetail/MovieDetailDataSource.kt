package com.destanti.MovieDB.data.remote.MovieDetail

import com.destanti.MovieDB.application.injection.AppConstants
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.data.Model.VideoResult
import com.destanti.MovieDB.data.remote.WebService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MovieDetailDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getVideoList(movieId: Int): Flow<Resource<List<VideoResult>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    webService.getVideoDetail(movieId, AppConstants.API_KEY)?.results ?: listOf()
                )
            )
            awaitClose { close() }
        }
}