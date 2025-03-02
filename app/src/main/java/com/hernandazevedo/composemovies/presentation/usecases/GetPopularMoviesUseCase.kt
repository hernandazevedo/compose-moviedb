package com.hernandazevedo.composemovies.presentation.usecases

import com.hernandazevedo.composemovies.domain.IMoviesRepository
import com.hernandazevedo.composemovies.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val iMoviesRepository: IMoviesRepository
) {
    suspend operator fun invoke(
        apiKey: String,
        language: String,
        page: Int
    ) = iMoviesRepository.getPopularMovies(
        apiKey, language, page
    ).map { it.results.toDomainModel() }
}