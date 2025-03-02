package com.hernandazevedo.composemovies.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun <T> callNetworkFlow(call: suspend () -> T): Flow<T> =
    flow {
        val response = call.invoke()
        emit(response)
    }.flowOn(Dispatchers.IO)
