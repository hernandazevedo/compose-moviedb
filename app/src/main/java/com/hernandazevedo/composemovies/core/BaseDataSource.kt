package com.hernandazevedo.composemovies.core

import com.google.gson.Gson
import com.hernandazevedo.composemovies.core.exceptions.ErrorResponse
import com.hernandazevedo.composemovies.core.exceptions.InvalidExceptionGeneral
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    //for testing force error
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): T {
        try {
            Timber.e("getResult for datasource")
           val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return body
            }

            if(response.code() in 400..499){
                val errorResponse = Gson().fromJson(response.errorBody()?.string()?:"", ErrorResponse::class.java)
                Timber.tag("BaseDataSource").e("Error 400 -- %s", errorResponse.error.message)
                throw InvalidExceptionGeneral(errorResponse.error.message?:"Error 400")
            }
            throw Exception(" not e ${response.code()} ${response.body()}")
        }catch (e:Throwable){
            error(e.message?:"")
        }
    }
}