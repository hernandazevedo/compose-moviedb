package com.hernandazevedo.composemovies.core

interface HttpClient {
    fun get(url: String, callback: okhttp3.Callback)
    fun post(url: String, jsonBody: String, callback: okhttp3.Callback)
}
