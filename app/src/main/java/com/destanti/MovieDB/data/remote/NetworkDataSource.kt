package com.destanti.MovieDB.data.remote

import com.destanti.MovieDB.application.injection.AppConstants
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.Genre
import com.destanti.MovieDB.data.Model.MovieListResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getMovieList(genreId:Int, page:Int): Flow<Resource<List<MovieListResult>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    webService.getMovieListByGenre(AppConstants.API_KEY,80,page)?.results ?: listOf()
                )
            )
            awaitClose { close() }
        }


    suspend fun getGenreList(): Flow<Resource<List<Genre>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    webService.getGenreList(AppConstants.API_KEY)?.genres ?: listOf()
                )
            )
            awaitClose { close() }
        }
}