package com.hernandazevedo.composemovies.domain

import kotlinx.coroutines.flow.Flow

interface IMoviesRemoteDataSource {
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

}
