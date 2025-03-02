package com.hernandazevedo.composemovies.data.remote
import com.hernandazevedo.composemovies.core.BaseDataSource
import com.hernandazevedo.composemovies.domain.MoviesDetailResponse
import com.hernandazevedo.composemovies.domain.PopularsMovieResponse
import javax.inject.Inject

interface IMoviesService {
    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): PopularsMovieResponse

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): MoviesDetailResponse

    suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String,
    ): PopularsMovieResponse
}


class MoviesServiceImpl @Inject constructor(
    private val movieService: MovieService
):BaseDataSource() , IMoviesService {
    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    )  = getResult (
        call = { movieService.getPopularMovies(api_key = apiKey, language = language, page = page) }
    )


    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = getResult (
        call = { movieService.getMovieDetail(api_key = apiKey, language = language, id = id) }
    )

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ) = getResult (
        call = { movieService.searchMovie(query = query, api_key = apiKey, language = language) }
    )
}