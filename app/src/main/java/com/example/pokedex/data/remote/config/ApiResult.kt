package com.example.pokedex.data.remote.config

sealed class ApiResult<out H> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val error: Throwable) : ApiResult<Nothing>()
}