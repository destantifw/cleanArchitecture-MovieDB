package com.destanti.MovieDB.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.destanti.MovieDB.data.Model.MovieEntity

@Database(entities = [MovieEntity::class],version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}