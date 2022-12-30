package com.example.pokedex.data.executor

import com.example.pokedex.core.common.exception.BackendException
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import javax.inject.Inject
import retrofit2.HttpException
import retrofit2.Response

class SourceExecutor @Inject constructor(
    private val moshi: Moshi
) {

    suspend fun <T> execute(request: suspend () -> Response<T>): SourceResult<T> {
        return try {
            val result = request()
            result.toSourceResult()
        } catch (httpException: HttpException) {
            httpException.toSourceResult()
        } catch (ex: Exception) {
            ex.toSourceResult()
        }
    }

    private fun <T> Throwable.toSourceResult(): SourceResult<T> {
        return SourceResult(
            isSuccessful = false,
            throwable = this
        )
    }

    private fun <T> HttpException.toSourceResult(): SourceResult<T> {
        val headers = this.response()?.headers()?.toMultimap().orEmpty()
        val throwable = this.response()?.buildBackendException()
        return SourceResult(
            isSuccessful = false,
            code = code(),
            headers = headers,
            throwable = throwable
        )
    }

    private fun <T> Response<T>.toSourceResult(): SourceResult<T> {
        val headers = this.headers().toMultimap()
        val throwable = if (!this.isSuccessful) this.buildBackendException() else null
        return SourceResult(
            isSuccessful = isSuccessful,
            body = body(),
            code = code(),
            headers = headers,
            throwable = throwable
        )
    }

    private fun <T> Response<T>.buildBackendException(): BackendException {
        val errorJson = this.errorBody()?.string()
        val errorBodyDto = runCatching {
            moshi.adapter(ErrorBodyDto::class.java).fromJson(errorJson!!)
        }.getOrNull()
        return BackendException(errorBodyDto?.errorType, errorBodyDto?.description)
    }

    private data class ErrorBodyDto(
        @Json(name = "error_type")
        val errorType: String,
        @Json(name = "description")
        val description: String,
    )
}
