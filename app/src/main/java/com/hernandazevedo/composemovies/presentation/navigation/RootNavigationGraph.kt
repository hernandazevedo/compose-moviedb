package com.hernandazevedo.composemovies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        composable(route = Graph.HOME) {

        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}