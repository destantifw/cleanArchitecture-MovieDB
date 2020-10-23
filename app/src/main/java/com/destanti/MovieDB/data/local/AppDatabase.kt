package com.destanti.MovieDB.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.destanti.MovieDB.data.Model.MovieEntity
import com.destanti.MovieDB.data.Model.VideoEntity

@Database(entities = [MovieEntity::class, VideoEntity::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}