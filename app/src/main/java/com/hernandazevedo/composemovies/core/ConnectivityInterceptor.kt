package com.hernandazevedo.composemovies.core

import com.hernandazevedo.composemovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .addHeader("User-Agent", "composemovies")
            .build()

        println("Sending request to ${modifiedRequest.url}")

        return chain.proceed(modifiedRequest)
    }
}