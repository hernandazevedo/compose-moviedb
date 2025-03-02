package com.hernandazevedo.composemovies.domain

import com.hernandazevedo.composemovies.domain.local.FavoriteMoviesEntity
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<PopularsMovieResponse>

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): Flow<MoviesDetailResponse>

    suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String,
    ): Flow<PopularsMovieResponse>


    //Database
    fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>>
    fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity>
    suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity)
    suspend fun deleteFavoriteMovie(id: Int)

}