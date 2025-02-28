package com.hernandazevedo.composemovies.core

import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class OkHttpClientImpl : HttpClient {

    private val client = OkHttpClient()

    override fun get(url: String, callback: Callback) {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
    }

    //TODO add headers
    override fun post(url: String, jsonBody: String, callback: Callback) {
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonBody.toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
    }
}
