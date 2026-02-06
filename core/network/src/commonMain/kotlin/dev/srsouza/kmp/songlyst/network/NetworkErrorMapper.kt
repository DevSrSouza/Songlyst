package dev.srsouza.kmp.songlyst.network

import dev.srsouza.kmp.songlyst.errorhandling.NetworkError
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.ContentConvertException

public fun Throwable.toNetworkError(): NetworkError =
    when (this) {
        is HttpRequestTimeoutException,
        is ConnectTimeoutException,
        is SocketTimeoutException,
        -> NetworkError.Timeout()
        is ResponseException -> {
            when (response.status) {
                HttpStatusCode.InternalServerError,
                HttpStatusCode.BadGateway,
                HttpStatusCode.ServiceUnavailable,
                HttpStatusCode.GatewayTimeout,
                -> NetworkError.ServerError()
                else -> NetworkError.Unknown(message)
            }
        }
        is ContentConvertException -> NetworkError.Unknown(message)
        else -> NetworkError.Unknown(message)
    }
