package com.hernandazevedo.composemovies.core.exceptions


class InvalidExceptionGeneral(message: String): Exception(message)

class NoConnectivityException(message: String): Exception(message)
data class ErrorResponse(
    val code: Int,
    val message: String,
    val error: Error
)