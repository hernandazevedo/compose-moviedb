package com.hernandazevedo.composemovies.di

import com.hernandazevedo.composemovies.core.HttpClient
import com.hernandazevedo.composemovies.core.OkHttpClientImpl

class AppModule() {
    val httpClient: HttpClient by lazy {
        OkHttpClientImpl()
    }
}