package com.hernandazevedo.composemovies.data.repository

import com.hernandazevedo.composemovies.data.local.IFavoriteMoviesLocalDataSource
import com.hernandazevedo.composemovies.data.remote.IMoviesRemoteDataSource
import com.hernandazevedo.composemovies.domain.IMoviesRepository
import com.hernandazevedo.composemovies.domain.PopularsMovieResponse
import com.hernandazevedo.composemovies.domain.local.FavoriteMoviesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remote: IMoviesRemoteDataSource,
    private val local: IFavoriteMoviesLocalDataSource
) : IMoviesRepository {

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ) = remote.getPopularMovies(apiKey, page,language)

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = remote.getMovieDetail(apiKey, id, language)

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ): Flow<PopularsMovieResponse> {
        return remote.searchMovie(apiKey, query, language)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>> {
        return local.getFavoriteMovies()
    }

    override fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity> {
        return local.getFavoriteMovieById(id)
    }

    override suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity) {
        local.insertFavoriteMovie(favoriteMoviesEntity)
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        local.deleteFavoriteMovie(id)
    }
}