package com.hernandazevedo.composemovies.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hernandazevedo.composemovies.R
import com.hernandazevedo.composemovies.domain.MovieDomain
import com.hernandazevedo.composemovies.presentation.composables.CustomEmptySearchScreen
import com.hernandazevedo.composemovies.presentation.composables.CustomErrorScreenSomethingHappens
import com.hernandazevedo.composemovies.presentation.composables.CustomNoInternetConnectionScreen
import com.hernandazevedo.composemovies.presentation.composables.HorizontalMovieItem
import com.hernandazevedo.composemovies.presentation.composables.LoadingScreen
import com.hernandazevedo.composemovies.presentation.viewmodels.PopularMoviesResult
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(moviesList: PopularMoviesResult,
                 onClickNavigateToDetails: (Int) -> Unit,
                 onQueryChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val searchQuery by rememberSaveable {
            mutableStateOf("")
        }
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(

            query = searchQuery,
            onQueryChange = onQueryChange,
            onSearch = { _ ->
                // Handle search ImeAction.Search here
            },
            active = true,
            onActiveChange = { isActive ->
            },
            placeholder = { Text("Search movie") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        ) {

        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hello 1",
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        HeaderMoviesScreen(
            searchQuery = searchQuery,
            onClickNavigateToDetails = onClickNavigateToDetails,
            popularMoviesState = moviesList
        )

    }
}


@Composable
fun HeaderMoviesScreen(
    searchQuery: String,
    onClickNavigateToDetails: (Int) -> Unit,
    popularMoviesState:  PopularMoviesResult
) {
    var isErrorGeneral by rememberSaveable { mutableStateOf(false) }
    var isSuccess by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var isEmpty by rememberSaveable { mutableStateOf(false) }
    var isInternetError by rememberSaveable { mutableStateOf(false) }

    var popularMoviesList by rememberSaveable { mutableStateOf(listOf<MovieDomain>()) }

    LaunchedEffect(key1 = popularMoviesState){
        when(popularMoviesState){
            PopularMoviesResult.Empty ->{
                isLoading = false
                isErrorGeneral = false
                isInternetError = false
                isEmpty = true
            }
            is PopularMoviesResult.ErrorGeneral -> {
                isLoading = false
                isErrorGeneral = true
            }
            PopularMoviesResult.InternetError -> {
                isErrorGeneral = false
            }
            is PopularMoviesResult.Loading -> {
                isLoading = popularMoviesState.isLoading
                isErrorGeneral = false
            }
            is PopularMoviesResult.Success -> {
                isLoading = false
                isErrorGeneral = false
                isEmpty = false
                isInternetError = false
                isSuccess = true
                popularMoviesList = popularMoviesState.list
            }
        }
    }

    when {
        isLoading -> {
            LoadingScreen()
        }
        isErrorGeneral -> {
            CustomErrorScreenSomethingHappens(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }
        isInternetError -> {
            CustomNoInternetConnectionScreen(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }
        isEmpty -> {
            CustomEmptySearchScreen(
                Modifier.padding(bottom = 180.dp),
                description = stringResource(id = R.string.empty_screen_description_no_results, searchQuery)
            )
        }
        isSuccess -> {
            LazyColumn {
                items(popularMoviesList) {

                    HorizontalMovieItem(
                        title = it.title,
                        imageUrl = it.poster_path,
                        rating = it.vote_average,
                        releaseDate = it.release_date?: "",
                        onClick = { onClickNavigateToDetails(it.id) })

                    if(it == popularMoviesList.last()) {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun MoviesScreenPrev() {
    val moviesTests = listOf(
        MovieDomain(
            id = 1,
            title = "Ant-Man y la Avispa: Quantumanía",
            overview = "La pareja de superhéroes Scott Lang y Hope van Dyne regresa para continuar sus aventuras como Ant-Man y la Avispa. Los dos, junto a los padres de Hope, Hank Pym y Janet van Dyne y la hija de Scott, Cassie Lang, se dedican a explorar el Mundo Cuántico, interactuando con nuevas y extrañas criaturas y embarcándose en una aventura que les llevará más allá de los límites de lo que creían posible.",
            poster_path = "https://image.tmdb.org/t/p/original/lKHy0ntGPdQeFwvNq8gK1D0anEr.jpg",
            vote_average = 6.5f,
            release_date = "2022-02-17"
        ),
        MovieDomain(
            id = 2,
            title = "Sisu",
            overview = "En lo profundo de la naturaleza salvaje de Laponia, Aatami Korpi está buscando oro, pero después de tropezar con una patrulla nazi, comienza una persecución impresionante y hambrienta de oro a través de la naturaleza salvaje de Laponia destruida y minada.",
            poster_path = "https://image.tmdb.org/t/p/original/t9VXZkgcxpIwfPUKAWOOONs0vHv.jpg",
            vote_average = 7.4f,
            release_date = "2021-10-01"
        ),
    )

    MoviesScreen(
        moviesList = PopularMoviesResult.Success(moviesTests),
        onClickNavigateToDetails = {
            Timber.d("onClickNavigateToDetails: $it")
        },
        onQueryChange = {
            Timber.d("onQueryChange: $it")
        }
    )
}