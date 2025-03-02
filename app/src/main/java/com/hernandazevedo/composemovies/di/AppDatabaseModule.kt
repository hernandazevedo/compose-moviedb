package com.hernandazevedo.composemovies.di

import android.content.Context
import androidx.room.Room
import com.hernandazevedo.composemovies.data.database.AppDatabase
import com.hernandazevedo.composemovies.data.database.dao.FavoriteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun providesFavoriteMoviesDao(appDatabase: AppDatabase): FavoriteMovieDao = appDatabase.favoriteMoviesDao()

    fun providesAppDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext, AppDatabase::class.java, "composemovies"
    )

}