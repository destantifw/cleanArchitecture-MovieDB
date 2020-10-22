package com.destanti.MovieDB.application.injection

import com.destanti.MovieDB.domain.DefaultMovieRepository
import com.destanti.MovieDB.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: DefaultMovieRepository): MovieRepository
}