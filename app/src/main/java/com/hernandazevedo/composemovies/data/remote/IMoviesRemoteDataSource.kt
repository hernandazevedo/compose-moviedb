package com.hernandazevedo.composemovies.data.remote

import com.hernandazevedo.composemovies.core.callNetworkFlow
import com.hernandazevedo.composemovies.domain.MoviesDetailResponse
import com.hernandazevedo.composemovies.domain.PopularsMovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IMoviesRemoteDataSource {
    suspend fun getPopularMovies(
        apiKey: String,
        page: Int,
        language: String
    ): Flow<PopularsMovieResponse>

    suspend fun getMovieDetail(
        apiKey: String,
        id: String,
        language: String,

        ): Flow<MoviesDetailResponse>

    suspend fun searchMovie(
        apiKey: String,
        query: String,
        language: String,
    ): Flow<PopularsMovieResponse>
}


class MoviesRemoteDataSource @Inject constructor(private val movieService: IMoviesService) :
    IMoviesRemoteDataSource {
    override suspend fun getPopularMovies(
        apiKey: String,
        page: Int,
        language: String
    ): Flow<PopularsMovieResponse> = callNetworkFlow {
        movieService.getPopularMovies(apiKey, language, page)
    }

    override suspend fun getMovieDetail(
        apiKey: String,
        id: String,
        language: String
    ): Flow<MoviesDetailResponse> = callNetworkFlow {
        movieService.getMovieDetail(apiKey, language, id)
    }

    override suspend fun searchMovie(
        apiKey: String,
        query: String,
        language: String
    ): Flow<PopularsMovieResponse> = callNetworkFlow {
        movieService.searchMovie(query, apiKey, language)
    }

}