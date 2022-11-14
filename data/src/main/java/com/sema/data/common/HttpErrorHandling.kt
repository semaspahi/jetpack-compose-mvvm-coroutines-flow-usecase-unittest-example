package com.sema.data.common

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun parseRetrofitError(callback: (Error) -> Unit): (Throwable) -> Unit =
    { callback(HttpErrorParser.parseError(it)) }

object HttpErrorParser {

    fun parseError(throwable: Throwable?): Error {
        return when (throwable) {
            is NoContentException -> Error(ErrorType.NoContent)
            is HttpException -> {
                val statusCode = throwable.code()
                when (statusCode) {
                    401 -> return Error(type = ErrorType.Unauthorized, statusCode = statusCode)
                }
                if (statusCode >= 500)
                    return Error(type = ErrorType.Unknown, statusCode = statusCode)

                if (statusCode == 404)
                    return Error(type = ErrorType.Unknown, statusCode = statusCode)

                val gson = Gson()
                var errorKey = ""
                val message = gson.fromJson<JsonObject>(
                    throwable.response()?.errorBody()?.string() ?: "", JsonElement::class.java
                ).run {
                    if (has("error_key"))
                        errorKey = get("error_key").asString
                    when {
                        has("error_message") -> get("error_message").asString
                        has("message") -> get("message").asString
                        else -> this.toString()
                    }
                }

                Error(ErrorType.Http, HttpErrorData(statusCode, message.toString(), errorKey), statusCode)
            }
            is SocketTimeoutException -> Error(ErrorType.Timeout)
            is IOException -> {
                Error(ErrorType.NetworkOffline)
            }
            else -> Error(ErrorType.Unknown)
        }
    }
}
class NoContentException : Exception()

data class Error(
    val type: ErrorType,
    val data: HttpErrorData? = null,
    val statusCode: Int? = null
)

enum class ErrorType {
    Http,
    Unauthorized,
    NoContent,
    Timeout,
    NetworkOffline,
    Unknown
}

data class HttpErrorData(
    val status: Int,
    val message: String?,
    val errorKey: String?,
)
