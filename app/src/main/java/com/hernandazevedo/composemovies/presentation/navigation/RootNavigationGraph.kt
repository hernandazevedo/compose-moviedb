package com.hernandazevedo.composemovies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hernandazevedo.composemovies.R
import com.hernandazevedo.composemovies.presentation.screens.DashboardScreen
import com.hernandazevedo.composemovies.presentation.screens.MoviesScreen
import com.hernandazevedo.composemovies.presentation.viewmodels.MoviesViewModel
import timber.log.Timber

@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        composable(route = Graph.HOME) {
            DashboardScreen()
        }
    }
}

@Composable
fun homeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, route = Graph.HOME,
        startDestination = HomeScreen.MoviesHomeScreen.route
    ) {
        composable(HomeScreen.MoviesHomeScreen.route) {
        val moviesViewModel = hiltViewModel<MoviesViewModel>()
        val moviesState by moviesViewModel.movies.collectAsStateWithLifecycle()
        MoviesScreen(
            moviesList = moviesState,
            onClickNavigateToDetails = { movieID ->
                Timber.d("Navigate to details movieID: $movieID")
                navController.navigate(route = Graph.DETAILS + "/$movieID")
            },
            onQueryChange = { query ->
                moviesViewModel.searchMovieOrEmpty(query)
            }
        )
        }
    }

}

sealed class HomeScreen(val route: String, val icon: Int, val title: String) {
    object MoviesHomeScreen : HomeScreen("movies_screen", R.drawable.ic_movie, "Movies")
    object FavoritesHomeScreen : HomeScreen("favorites_screen", R.drawable.ic_love, "Favorites")
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}
