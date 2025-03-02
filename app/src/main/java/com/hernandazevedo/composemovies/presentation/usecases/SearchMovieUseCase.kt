package com.hernandazevedo.composemovies.presentation.usecases

import com.hernandazevedo.composemovies.domain.IMoviesRepository
import com.hernandazevedo.composemovies.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(private val iMoviesRepository: IMoviesRepository) {
    suspend operator fun invoke(query: String,apiKey: String,
                                language: String) =
        iMoviesRepository.searchMovie(query,apiKey,language).map {
            it.results.toDomainModel()
        }
}