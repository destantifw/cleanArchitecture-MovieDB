package com.destanti.MovieDB.domain

import androidx.lifecycle.LiveData
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.MovieEntity
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.data.Model.asMovieEntity
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
class DefaultMovieRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : MovieRepository {

    override suspend fun getMovieList(page: Int): Flow<Resource<List<MovieListResult>>> =
        callbackFlow {

//            offer(getCachedAllMovies())

            networkDataSource.getMovieList(80, page).collect{
                when(it){
                    is Resource.Success -> {
                        for (movie in it.data) {
                            saveMovie(movie.asMovieEntity())
                        }
                        offer(getCachedAllMovies())
                    }
                    is Resource.Failure -> {

                    }
                }
            }
            awaitClose { cancel() }
        }

    override suspend fun saveMovie(movie: MovieEntity) {
        localDataSource.saveMovie(movie)
    }

    override suspend fun getCachedMoviesByGenre(genreId: Int): Resource<List<MovieListResult>> {

        return localDataSource.getCachedMoviesByGenre(genreId)
    }

    override suspend fun getCachedAllMovies(): Resource<List<MovieListResult>> {
        return localDataSource.getCachedMovies()
    }


}