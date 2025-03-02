package com.hernandazevedo.composemovies.di

import com.hernandazevedo.composemovies.data.local.FavoriteMoviesLocalDataSourceImpl
import com.hernandazevedo.composemovies.data.local.IFavoriteMoviesLocalDataSource
import com.hernandazevedo.composemovies.data.remote.IMoviesService
import com.hernandazevedo.composemovies.data.remote.MovieService
import com.hernandazevedo.composemovies.data.remote.IMoviesRemoteDataSource
import com.hernandazevedo.composemovies.data.remote.MoviesRemoteDataSource
import com.hernandazevedo.composemovies.data.remote.MoviesServiceImpl
import com.hernandazevedo.composemovies.data.repository.MoviesRepository
import com.hernandazevedo.composemovies.domain.IMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {

    //Remote
    @Singleton
    @Binds
    abstract fun provideMovieServices(
        moviesServiceImpl: MoviesServiceImpl
    ): IMoviesService

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSource: MoviesRemoteDataSource
    ): IMoviesRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        moviesRepositoryImpl: MoviesRepository
    ): IMoviesRepository


    //Local

    @Singleton
    @Binds
    abstract fun provideMoviesLocalDataSource(
        moviesLocalDataSourceImpl: FavoriteMoviesLocalDataSourceImpl
    ): IFavoriteMoviesLocalDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object MoviesModuleObj {

    @Singleton
    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}
