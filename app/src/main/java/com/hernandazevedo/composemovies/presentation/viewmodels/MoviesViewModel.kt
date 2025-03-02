package com.hernandazevedo.composemovies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hernandazevedo.composemovies.BuildConfig
import com.hernandazevedo.composemovies.core.exceptions.NoConnectivityException
import com.hernandazevedo.composemovies.domain.MovieDomain
import com.hernandazevedo.composemovies.presentation.usecases.GetPopularMoviesUseCase
import com.hernandazevedo.composemovies.presentation.usecases.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _moviesState =
        MutableStateFlow<PopularMoviesResult>(PopularMoviesResult.Loading(false))
    val movies = _moviesState.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000, 1),
        initialValue = PopularMoviesResult.Loading(false)
    )

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        getPopularMoviesUseCase.invoke(apiKey = BuildConfig.API_KEY, language = "en-US", page = 1)
            .onStart {
                _moviesState.value = PopularMoviesResult.Loading(true)
            }
            .onEach {
                _moviesState.value = PopularMoviesResult.Success(it)
            }
            .catch {
                when (it) {
                    is NoConnectivityException -> {
                        _moviesState.value = PopularMoviesResult.InternetError
                    }

                    else -> {
                        _moviesState.value =
                            PopularMoviesResult.ErrorGeneral(it.message ?: "Error general")
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun searchMovieOrEmpty(query: String) {
        if (query.isEmpty()) {
            getPopularMovies()
            return
        }
        // Search movie only if query is not empty
        searchMovie(query)
    }

    fun searchMovie(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchMovieUseCase.invoke(apiKey = BuildConfig.API_KEY, language = "en-US", query = query)
            .onStart {
                _moviesState.value = PopularMoviesResult.Loading(true)
            }
            .onEach { _moviesState.value = PopularMoviesResult.Success(it) }
            .catch {
                when (it) {
                    is NoConnectivityException -> {
                        _moviesState.value = PopularMoviesResult.InternetError
                    }

                    else -> {
                        _moviesState.value =
                            PopularMoviesResult.ErrorGeneral(it.message ?: "Error general")
                    }
                }
            }.launchIn(viewModelScope)
    }
}

sealed class PopularMoviesResult {
    data class Success(val list: List<MovieDomain>) : PopularMoviesResult()
    data class ErrorGeneral(val error: String) : PopularMoviesResult()
    data class Loading(val isLoading: Boolean) : PopularMoviesResult()
    object InternetError : PopularMoviesResult()
    object Empty : PopularMoviesResult()
}