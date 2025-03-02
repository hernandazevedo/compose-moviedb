package com.hernandazevedo.composemovies.data.local

import com.hernandazevedo.composemovies.data.database.dao.FavoriteMovieEntityDao
import com.hernandazevedo.composemovies.domain.local.FavoriteMoviesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IFavoriteMoviesLocalDataSource {
    fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>>
    fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity>
    suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity)
    suspend fun deleteFavoriteMovie(id: Int)
}

class FavoriteMoviesLocalDataSourceImpl @Inject constructor(
    private val moviesDao: FavoriteMovieEntityDao
):IFavoriteMoviesLocalDataSource{
    override fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>> {
        return moviesDao.getAll()
    }

    override fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity> {
        return moviesDao.getById(id)
    }

    override suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity) {
        return moviesDao.insert(favoriteMoviesEntity)
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        return moviesDao.deleteById(id)
    }

}