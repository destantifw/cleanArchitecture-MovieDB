package com.destanti.MovieDB.Base

import android.content.Context
import com.example.destanti.submission2.Services.MovieRepository

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        return MovieRepository.getInstance(MovieRemoteDataSource.getInstance())
    }
}