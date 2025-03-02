package com.hernandazevedo.composemovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hernandazevedo.composemovies.data.database.dao.FavoriteMovieDao
import com.hernandazevedo.composemovies.domain.local.FavoriteMoviesEntity

@Database(entities = [FavoriteMoviesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteMovieDao
}