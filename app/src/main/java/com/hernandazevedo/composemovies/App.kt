package com.hernandazevedo.composemovies

import androidx.compose.runtime.Composable
import com.hernandazevedo.composemovies.presentation.navigation.RootNavigationGraph
import com.hernandazevedo.composemovies.ui.theme.MoviesappTheme

@Composable
fun App(darkTheme: Boolean,
        dynamicColor: Boolean) {
    MoviesappTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        RootNavigationGraph()
    }
}