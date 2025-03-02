package com.hernandazevedo.composemovies.di

import android.content.Context
import androidx.room.Room
import com.hernandazevedo.composemovies.data.database.AppDatabase
import com.hernandazevedo.composemovies.data.database.dao.FavoriteMovieEntityDao
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
    fun providesAppDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext, AppDatabase::class.java, "composemovies"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesFavoriteMoviesDao(appDatabase: AppDatabase): FavoriteMovieEntityDao = appDatabase.favoriteMoviesDao()
}