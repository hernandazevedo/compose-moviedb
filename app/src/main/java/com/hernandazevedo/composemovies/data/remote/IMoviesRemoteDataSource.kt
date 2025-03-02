package com.hernandazevedo.composemovies.data.remote

import com.hernandazevedo.composemovies.core.callNetworkFlow
import com.hernandazevedo.composemovies.domain.MoviesDetailResponse
import com.hernandazevedo.composemovies.domain.PopularsMovieResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRemoteDataSource {
    suspend fun getPopularMovies(apiKey: String,
                                 page: Int,
                                 language: String): Flow<PopularsMovieResponse>

    suspend fun getMovieDetail(
        apiKey: String,
        id: String,
        language: String,

    ): Flow<MoviesDetailResponse>

    suspend fun searchMovie(
        api_key: String,
        query: String,
        language: String,
    ): Flow<PopularsMovieResponse>
}


class IMoviesRemoteDataSourceImpl(private val movieService: IMoviesService) :
    IMoviesRemoteDataSource {
    override suspend fun getPopularMovies(
        apiKey: String,
        page: Int,
        language: String
    ): Flow<PopularsMovieResponse> = callNetworkFlow {
        movieService.getPopularMovies(apiKey,language,page)
    }

    override suspend fun getMovieDetail(
        apiKey: String,
        id: String,
        language: String
    ): Flow<MoviesDetailResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(
        api_key: String,
        query: String,
        language: String
    ): Flow<PopularsMovieResponse> {
        TODO("Not yet implemented")
    }

}